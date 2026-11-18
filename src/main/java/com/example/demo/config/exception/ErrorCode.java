package com.example.demo.config.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter 
@RequiredArgsConstructor 
public enum ErrorCode {
    
    // 400
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "C001", "입력값이 올바르지 않습니다"),
    
    // 404
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "회원을 찾을 수 없습니다"),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "D001", "데이터를 찾을 수 없습니다"),
    // 405 
    METHOD_NOT_ALLOW(HttpStatus.METHOD_NOT_ALLOWED, "C002", "지원하지 않는 HTTP 메서드 입니다."),

    // 409
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "M002", "이미 사용중인 이메일입니다"), 
    CAPACITY_EXCEEDED(HttpStatus.CONFLICT, "E001", "정원이 초과되었습니다."),
    DATA_INTEGRITY_VIOLATION(HttpStatus.CONFLICT, "C003", "데이터 조건을 위배했습니다."), 
    INVALID_SORT(HttpStatus.CONFLICT,"C004","정렬조건을 위배했습니다."),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C999", "서버 오류가 발생했습니다")
    ;

    HttpStatus status;
    String message;
    String code;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
