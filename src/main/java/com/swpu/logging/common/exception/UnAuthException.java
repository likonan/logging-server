package com.swpu.logging.common.exception;

public class UnAuthException extends RuntimeException {


    public UnAuthException(String msg) {
        super(msg);
    }

    public UnAuthException() {
        super();
    }


}
