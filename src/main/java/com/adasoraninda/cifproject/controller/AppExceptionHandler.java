package com.adasoraninda.cifproject.controller;

import com.adasoraninda.cifproject.exception.BusinessException;
import com.adasoraninda.cifproject.model.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler {

    private final String code = "ERROR";

    @ExceptionHandler(value = {Exception.class, Error.class})
    public ResponseEntity<Object> handleAllError(Throwable ex) {
        var message = ex.getCause().getCause().getLocalizedMessage();

        if(message.contains("Detail:")){
            var resultBeforeEqual = message.substring(message.indexOf("(")+1,message.indexOf(")"));
            var resultAfterEqual = message.substring(message.indexOf("="));
            var resultAfterEqualSplit = resultAfterEqual.substring(resultAfterEqual.indexOf(" "));
            var value =  resultAfterEqual.substring(resultAfterEqual.indexOf("(")+1,resultAfterEqual.indexOf(")"));

            message = resultBeforeEqual + " = " + value + resultAfterEqualSplit;
        }

        var errorResponse = new ErrorResponse<>(code, message);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handlePathError(NoHandlerFoundException ex) {
        var errorResponse = new ErrorResponse<>(code, ex.getLocalizedMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInputError(MethodArgumentNotValidException ex) {
        var message = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        var errorResponse = new ErrorResponse<>(code, message);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(errorResponse);
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleAppError(Exception ex) {
        var errorResponse = new ErrorResponse<>(ex.toString(), ex.getLocalizedMessage());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(errorResponse);
    }

}
