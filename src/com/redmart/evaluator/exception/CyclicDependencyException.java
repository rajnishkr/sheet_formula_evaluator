package com.redmart.evaluator.exception;

public class CyclicDependencyException extends Exception {
    CyclicDependencyException(String message){
        super(message);
    }
}
