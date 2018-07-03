package com.redmart.evaluator.model;

import java.util.LinkedList;

public class Cell {

    private  int row;
    private  int col;
    private LinkedList<CellType> cellType;
    private  String data;
    private int referencesCount;
    private boolean isEvaluated;
    private double evaluatedValue;

    public Cell(int row, int col, String data){

        this.row = row;
        this.col = col;
        this.data = data;
        this.referencesCount = 0;
        this.cellType = new LinkedList<CellType>();
    }


}
