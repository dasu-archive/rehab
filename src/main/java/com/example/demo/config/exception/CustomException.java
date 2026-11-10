package com.example.demo.config.exception;

import lombok.Getter;

@Getter 
public class CustomException extends RuntimeException{

    Integer status;
    String message;
    String code;
    
    public CustomException(CustomExceptionEnums e){
        this.status = e.status;
        this.message = e.message;
        this.code = e.code;
    }
}
