package com.redmart.evaluator.service;

import com.redmart.evaluator.dao.SheetDao;
import com.redmart.evaluator.entity.Cell;
import com.redmart.evaluator.exception.CyclicDependencyException;
import com.redmart.evaluator.exception.ParsingException;
import com.redmart.evaluator.model.CellType;
import com.redmart.evaluator.model.OperatorTypeCell;
import com.redmart.evaluator.model.ReferenceTypeCell;
import com.redmart.evaluator.model.ValueTypeCell;

import javax.naming.InsufficientResourcesException;
import java.util.*;

public class SheetEvaluator {

    private final SheetDao sheetDao;
    private final LinkedList<Cell> topologicalSortArray;
    private final OperatorUtility operatorUtility;
    private final HashMap<ReferenceTypeCell, HashSet<Cell>> graph;
    private boolean cyclicDependency;
    private List<String> dataList;
    private int cellToResolved;

    public SheetEvaluator(int n, int m, List<String> dataList) throws InsufficientResourcesException, CyclicDependencyException, ParsingException {
        sheetDao = new SheetDao(n, m);
        topologicalSortArray = new LinkedList<Cell>();
        graph = new HashMap<ReferenceTypeCell, HashSet<Cell>>();
        cyclicDependency = false;
        this.dataList = dataList;
        if (dataList.size() < (n * m)) {
            throw new InsufficientResourcesException();
        }
        operatorUtility = new OperatorUtility();
        this.init();
        this.evaluate();
        this.display();

    }

    private void display() {
        Cell cells[][] = sheetDao.getSheet().getCells();
        int rowLen = cells.length;
        int colLen = cells[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (cells[row][col].isEvaluated()) {
                    System.out.println(IndexUtility.getRowName(row) + "" + (col + 1) +":"
                            +"formula=("+cells[row][col].getData()+") : " + String.format("%.3f",cells[row][col].getEvaluatedValue()));
                } else {
                    System.out.println(IndexUtility.getRowName(row) + ":" + (col + 1) + " : " +"cyclic");
                }
            }

        }
    }

    public void evaluate() throws CyclicDependencyException, ParsingException {
        while (topologicalSortArray.size() > 0) {
            Cell cell = topologicalSortArray.removeFirst();
            evaluate(cell);
            cellToResolved--;
            markResolveDependencies(cell);
        }
        if (cellToResolved != 0) {
            cyclicDependency = true;
           // throw new CyclicDependencyException("Cyclic Dependency Found");
        }
    }

    private void markResolveDependencies(Cell cell) {
        ReferenceTypeCell typeCell=new ReferenceTypeCell(cell.getRow(),cell.getCol());
        if (graph.containsKey(typeCell)) {
            HashSet<Cell> dependentCells = graph.get(typeCell);
            if (dependentCells.size() > 0) {
                for (Cell depCell : dependentCells) {
                    depCell.setReferencesCount(depCell.getReferencesCount() - 1);
                    if (depCell.getReferencesCount() == 0)
                        topologicalSortArray.add(depCell);
                }
            }
        }
    }

    private double evaluate(Cell cell) throws RuntimeException {
        if (cell.isEvaluated())
            return cell.getEvaluatedValue();

        Stack<Double> stack = new Stack<Double>();
        LinkedList<CellType> cellInfoList = cell.getCelldata();

        for (CellType cellType : cellInfoList) {
            if (cellType.getClass().equals(ValueTypeCell.class)) {
                stack.push(Double.parseDouble(((ValueTypeCell) cellType).getData()));
            } else if (cellType.getClass().equals(ReferenceTypeCell.class)) {
                ReferenceTypeCell refCellType = (ReferenceTypeCell) cellType;
                Cell refCell = sheetDao.getSheet().getCell(refCellType.getRefRow(), refCellType.getRefCol());
                stack.push(evaluate(refCell));
            } else if (cellType.getClass().equals(OperatorTypeCell.class)) {
                OperatorTypeCell optCellType = (OperatorTypeCell) cellType;
                operatorUtility.evaluate(optCellType.getValue(), stack);
            } else {
                throw new ParsingException("Error: Invalid expression: " + cell.toString(), 400);
            }
        }
        cell.setEvaluatedValue(stack.pop());
        cell.setEvaluated(true);
        return cell.getEvaluatedValue();
    }

    private void init() {
        Cell cells[][] = sheetDao.getSheet().getCells();
        int rowLen = cells.length;
        int colLen = cells[0].length;
        int counter = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                String data = dataList.get(counter++).toUpperCase();
                Cell cell = cells[row][col] = new Cell(row, col, data);
                if (cell.getReferencesCount() == 0) {
                    topologicalSortArray.add(cell);
                } else {
                    cellToResolved++;
                    addToGraph(cell);
                }
            }
        }
    }

    private void addToGraph(Cell cell) {
        LinkedList<ReferenceTypeCell> dependentNodes = cell.getReferenceTypeCells();

        for (ReferenceTypeCell refCell : dependentNodes) {
            HashSet<Cell> ref;
            if (graph.containsKey(refCell)) {
                ref = graph.get(refCell);
            } else {
                ref = new HashSet<>();

            }
            ref.add(cell);
            graph.put(refCell, ref);
        }
    }

}
