package com.redmart.evaluator.model;

import java.util.HashMap;
import java.util.Map;

public enum Operator {
    SUB("-"), ADD("+"), MUL("*"), DIV("/");
    private static final Map<String, Operator> map = new HashMap<String, Operator>();

    static {
        for (Operator op : Operator.values())
            map.put(op.getOperator(), op);
    }
    private final String value;
    Operator(String operator) {
        value = operator;
    }

    public static Operator getOperatorObject(String operator){
        return map.get(operator);
    }

    public static boolean isValidOperator(String data) {
        for (Operator op : Operator.values()) {
            if (op.value.equals(data)) {
                return true;
            }
        }
        return false;
    }

    public String getOperator() {
        return value;
    }

}
