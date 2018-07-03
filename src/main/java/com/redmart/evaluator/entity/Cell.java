package com.redmart.evaluator.entity;

import com.redmart.evaluator.builder.CellTypeParser;
import com.redmart.evaluator.exception.ParsingException;
import com.redmart.evaluator.model.CellType;
import com.redmart.evaluator.model.OperatorTypeCell;
import com.redmart.evaluator.model.ReferenceTypeCell;
import com.redmart.evaluator.service.IndexUtility;

import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Pattern;

public class Cell {

    private int row;
    private int col;
    private LinkedList<ReferenceTypeCell> referencedCells;
    private LinkedList<CellType> cellData;
    private String data;
    private int referencesCount;
    private boolean isEvaluated;
    private double evaluatedValue;
    private boolean invalidData;
    private CellTypeParser cellTypeParser;

    public Cell(int row, int col, String data) {

        this.row = row;
        this.col = col;
        this.data = data;
        this.referencesCount = 0;
        this.cellData = new LinkedList<CellType>();
        this.referencedCells = new LinkedList<ReferenceTypeCell>();
        this.cellTypeParser = new CellTypeParser();
        init();
    }

    private void init() throws ParsingException {

        Pattern splitRegex = Pattern.compile("\\s+");
        String[] input = splitRegex.split(data);
        if (!validExpression(input)) {
            // this.setReferencesCount(Integer.MAX_VALUE);
            setInvalidData(true);
            return;
        }
        for (String part : input) {
            CellType cellType = cellTypeParser.parseData(part);
            if (cellType.getClass().equals(ReferenceTypeCell.class)) {
                referencedCells.add((ReferenceTypeCell) cellType);
                referencesCount++;
            }
            cellData.add(cellType);
        }
    }

    private boolean validExpression(String[] input) {
        if (input.length == 0) {
            return true;
        }
        Stack<CellType> cellTypeStack = new Stack<CellType>();
        cellTypeStack.push(cellTypeParser.parseData(input[0]));
        for (int itr = 1; itr < input.length; itr++) {
            CellType cellType = cellTypeParser.parseData(input[itr]);
            if (cellType.getClass().equals(OperatorTypeCell.class)) {
                if (cellTypeStack.size() < 2) {
                    return false;
                } else {
                    cellTypeStack.pop();
                }
            } else {
                cellTypeStack.push(cellType);
            }
        }


        return cellTypeStack.size() == 1;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public LinkedList<ReferenceTypeCell> getReferencedCells() {
        return referencedCells;
    }

    public void setReferencedCells(LinkedList<ReferenceTypeCell> referencedCells) {
        this.referencedCells = referencedCells;
    }

    public LinkedList<CellType> getCellData() {
        return cellData;
    }

    public void setCellData(LinkedList<CellType> cellData) {
        this.cellData = cellData;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getReferencesCount() {
        return referencesCount;
    }

    public void setReferencesCount(int referencesCount) {
        this.referencesCount = referencesCount;
    }

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public void setEvaluated(boolean evaluated) {
        isEvaluated = evaluated;
    }

    public double getEvaluatedValue() {
        return evaluatedValue;
    }

    public void setEvaluatedValue(double evaluatedValue) {
        this.evaluatedValue = evaluatedValue;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + IndexUtility.getRowName(row) +
                ", col=" + col +
                ", data='" + data + '\'' +
                ", evaluatedValue=" + evaluatedValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;

        Cell cell = (Cell) o;

        return row == cell.row && col == cell.col;
    }

    @Override
    public int hashCode() {
        int result = row + 1;
        result = 31 * result + col + 1;
        return result;
    }

    public boolean isInvalidData() {
        return invalidData;
    }

    public void setInvalidData(boolean invalidData) {
        this.invalidData = invalidData;
    }
}
