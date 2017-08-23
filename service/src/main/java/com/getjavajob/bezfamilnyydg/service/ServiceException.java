package com.getjavajob.bezfamilnyydg.service;

import org.springframework.stereotype.Service;

public class ServiceException extends Exception {
    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
