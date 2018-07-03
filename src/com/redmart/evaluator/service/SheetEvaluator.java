package com.redmart.evaluator.service;

import com.redmart.evaluator.SheetDao;
import com.redmart.evaluator.entity.Cell;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SheetEvaluator {

    private final SheetDao sheetDao;
    private final LinkedList<Cell> topologicalList;
    private final HashMap<Integer, HashSet<Cell>> dependenciesMap;
    private boolean cyclicDependency;

    public SheetEvaluator(int n,int m){
        sheetDao=new SheetDao(n,m);
        topologicalList = new LinkedList<Cell>();
        dependenciesMap = new HashMap<Integer, HashSet<Cell>>();
        cyclicDependency=false;
    }

}
