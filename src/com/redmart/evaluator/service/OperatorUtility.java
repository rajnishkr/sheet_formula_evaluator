package com.redmart.evaluator.service;

import com.redmart.evaluator.model.Operator;

import java.util.Stack;

public class OperatorUtility {


    public void evaluate(Operator operator, Stack<Double> stack) throws IllegalArgumentException {
        double first, second;
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Error: Invalid Expression");
        }
        second = stack.pop();
        first = stack.pop();
        switch (operator) {
            case ADD:
                stack.push(first + second);
                break;
            case SUB:
                stack.push(first - second);
                break;
            case MUL:
                stack.push(first * second);
                break;
            case DIV:
                if (second == 0) {
                    throw new IllegalArgumentException("Error: divide by 0");
                }
                stack.push(first / second);
                break;
        }
        return;
    }
}
