package com.example.demo.config.exception;

public enum CustomExceptionEnums {
    
    USERNOTFOUND(400 , "400" , "User Not Found"),
    EVENTNOTFOUND(400, "400","Event Not Found")
    ;

    Integer status;
    String message;
    String code;

    CustomExceptionEnums(Integer status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
