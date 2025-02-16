package com.example.wisebet.domain.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message){
        super(message);
    }
}
