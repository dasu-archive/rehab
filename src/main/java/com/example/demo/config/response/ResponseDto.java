package com.example.demo.config.response;

import com.example.demo.config.exception.CustomException;

import lombok.Getter;

@Getter 
public class ResponseDto<T> {
    private Integer status;
    private String code;
    private String message; 
    private final T data;


    public ResponseDto(){
        this.status = 200;
        this.code = "ok";
        this.message = "ok";
        this.data = null;
    }
    public ResponseDto(T data){
        this.status = 200;
        this.code = "ok";
        this.message = "ok";
        this.data = data;
    }

    public ResponseDto(Integer status, String code, String message, T data){
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseDto(CustomException e){
        this.status = e.getStatus();
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = null;
    }
}
