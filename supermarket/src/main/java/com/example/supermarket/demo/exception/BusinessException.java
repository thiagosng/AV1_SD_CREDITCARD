package com.example.supermarket.demo.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(String msg, Throwable causa){
        super(msg, causa);
    }

}
