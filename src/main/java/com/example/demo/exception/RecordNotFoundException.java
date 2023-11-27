package com.example.demo.exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String url){
        super("Could not find url " + url);
    }
}
