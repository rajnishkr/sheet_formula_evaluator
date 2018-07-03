package com.redmart.evaluator.exception;

public class ParsingException extends RuntimeException {

    private final int code;

    public ParsingException(String message, int code) {
        super(message);
        this.code = code;
    }
}
