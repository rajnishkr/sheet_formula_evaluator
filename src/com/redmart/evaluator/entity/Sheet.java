package com.redmart.evaluator.entity;

public class Sheet {

    private Cell[][] cells;
    private int row;
    private int col;

   public Sheet(int row, int col) {
        this.row = row;
        this.col = col;
        cells = new Cell[row][col];
    }

    public Cell getCell(int row,int col) {
        return cells[row][col];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
