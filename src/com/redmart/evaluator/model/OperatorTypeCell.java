package com.redmart.evaluator.model;

public class OperatorTypeCell extends CellType {

    public OperatorTypeCell(Operator operator) {
        setData(operator.getOperator());
    }

    public Operator getValue() {
        return Operator.valueOf(getData());
    }

    public static boolean isValid(String data) {
        return Operator.isValidOperator(data);
    }
}
