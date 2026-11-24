package com.example.demo.global.config.exception;

import lombok.Getter;

@Getter 
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;
    
    public CustomException(ErrorCode e){
        
        this.errorCode = e;
    }
}
