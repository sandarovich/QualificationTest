package com.sandarovich.fileupload.validation;

public class ParseException extends RuntimeException {
    private static final long serialVersionUID = 7364451446089457089L;

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
