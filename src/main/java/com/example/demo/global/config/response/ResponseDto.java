package com.example.demo.global.config.response;

import org.springframework.http.ResponseEntity;

import com.example.demo.global.config.exception.ErrorCode;

import lombok.Getter;

@Getter 
public class ResponseDto<T>{
    private boolean success;
    private String code;
    private String message; 
    private final T data;

    private ResponseDto(boolean success, String code, String message, T data){
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T>  ResponseEntity<ResponseDto<T>> ok(T data){
        return ResponseEntity.ok(new ResponseDto<>(true, "ok","ok",data));
    }

    public static ResponseEntity<ResponseDto<Void>> fail(ErrorCode errorCode) {
        return new ResponseEntity<>(
            new ResponseDto<>(false, errorCode.getCode(), errorCode.getMessage(), null),
            errorCode.getStatus());
    }

    public static ResponseEntity<ResponseDto<Void>> fail(ErrorCode errorCode, String message) {
        return new ResponseEntity<>(
            new ResponseDto<>(false, errorCode.getCode(), message, null),
            errorCode.getStatus());
    }
}
