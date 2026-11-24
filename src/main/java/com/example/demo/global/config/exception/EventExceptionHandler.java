package com.example.demo.global.config.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.global.config.response.ResponseDto;

@ControllerAdvice 
public class EventExceptionHandler {


    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = (fieldError != null) ? fieldError.getDefaultMessage() : ErrorCode.INVALID_INPUT.getMessage();
        return ResponseDto.fail( ErrorCode.INVALID_INPUT, message);
    }
    @ExceptionHandler(value=DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto<Void>> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseDto.fail(ErrorCode.DATA_INTEGRITY_VIOLATION);
   
    }

    @ExceptionHandler(value=CustomException.class)
    public ResponseEntity<ResponseDto<Void>> handleCustomException(CustomException e){
        return ResponseDto.fail(e.getErrorCode());
    }
    
}
