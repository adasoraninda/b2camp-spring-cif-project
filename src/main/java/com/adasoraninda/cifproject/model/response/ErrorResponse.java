package com.adasoraninda.cifproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse<T> {
    private final String code;
    private final T message;
}
