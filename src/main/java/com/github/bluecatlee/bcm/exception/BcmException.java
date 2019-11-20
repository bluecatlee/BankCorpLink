package com.github.bluecatlee.bcm.exception;

public class BcmException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BcmException(String message, Throwable cause) {
        super(message, cause);
    }

    public BcmException(Throwable cause) {
        super(cause);
    }

    public BcmException(String message) {
        super(message);
    }

}