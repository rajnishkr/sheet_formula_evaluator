package com.redmart.evaluator;

import com.redmart.evaluator.entity.Sheet;

public class SheetDao {
    private Sheet sheet;
    public SheetDao(int row,int column){
        this.sheet= new Sheet(row,column);
    }

    public Sheet getSheet(){
        return this.sheet;
    }
}
