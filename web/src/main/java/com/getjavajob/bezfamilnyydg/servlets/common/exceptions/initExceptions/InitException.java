package com.getjavajob.bezfamilnyydg.servlets.common.exceptions.initExceptions;


public class InitException extends Exception {
    public InitException(Throwable e) {
        super(e);
    }

    public InitException(String msg) {
        super(msg);
    }
}
