package com.redmart.evaluator.model;

public enum  Operator {
    SUB("-"),ADD("+"), MUL("*"), DIV("/");

     Operator(String op) {
        value = op;
    }

    private final String value;
    public String getOperator() {
        return value;
    }

    public static boolean isValidOperator(String data){
        for (Operator op : Operator.values()){
            if(op.value.equals(data)){
                return true;
            }
        }
        return false;
    }

}
