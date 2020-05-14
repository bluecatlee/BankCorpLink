package com.github.bluecatlee.cib.exception;

public class CibException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CibException(String message, Throwable cause) {
        super(message, cause);
    }

    public CibException(Throwable cause) {
        super(cause);
    }

    public CibException(String message) {
        super(message);
    }

}