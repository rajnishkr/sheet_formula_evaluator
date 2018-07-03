package com.redmart.evaluator.entity;

import com.redmart.evaluator.builder.CellTypeParser;
import com.redmart.evaluator.exception.ParsingException;
import com.redmart.evaluator.model.CellType;
import com.redmart.evaluator.model.ReferenceTypeCell;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class Cell {

    private  int row;
    private  int col;
    private LinkedList<ReferenceTypeCell> referenceTypeCells;
    private LinkedList<CellType> celldata;
    private  String data;
    private int referencesCount;
    private boolean isEvaluated;
    private double evaluatedValue;

    public Cell(int row, int col, String data){

        this.row = row;
        this.col = col;
        this.data = data;
        this.referencesCount = 0;
        this.celldata = new LinkedList<>();
        this.referenceTypeCells = new LinkedList<>();
        init();
    }

    private void init() throws ParsingException {
        Pattern splitRegex = Pattern.compile("\\s+");
        String[] input = splitRegex.split(data);
        CellTypeParser cellTypeParser = new CellTypeParser();
        for (String part : input) {
            CellType cellType = cellTypeParser.parseData(part);
            if (cellType.getClass().equals(ReferenceTypeCell.class)) {
                referenceTypeCells.add((ReferenceTypeCell) cellType);
                referencesCount++;
            }
            celldata.add(cellType);
        }
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

    public LinkedList<ReferenceTypeCell> getReferenceTypeCells() {
        return referenceTypeCells;
    }

    public void setReferenceTypeCells(LinkedList<ReferenceTypeCell> referenceTypeCells) {
        this.referenceTypeCells = referenceTypeCells;
    }

    public LinkedList<CellType> getCelldata() {
        return celldata;
    }

    public void setCelldata(LinkedList<CellType> celldata) {
        this.celldata = celldata;
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
}
