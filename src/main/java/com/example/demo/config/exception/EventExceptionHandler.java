package com.example.demo.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.config.response.ResponseDto;

@ControllerAdvice 
public class EventExceptionHandler {

    @ExceptionHandler(value=CustomException.class)
    public ResponseDto handleCustomException(CustomException e){
        return new ResponseDto<>(e);
    }
    
}
