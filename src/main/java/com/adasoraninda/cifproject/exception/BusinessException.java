package com.adasoraninda.cifproject.exception;

import com.adasoraninda.cifproject.exception.message.ErrorMessage;

public class BusinessException extends RuntimeException {

    private final ErrorMessage message;

    public BusinessException(ErrorMessage message) {
        super(message.getMessage());
        this.message = message;
    }

    @Override
    public String toString() {
        return message.getCode();
    }
}
