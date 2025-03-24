package com.pattern.circuitbreaker.model;

public class TechnicalException extends RuntimeException{

    public TechnicalException(Throwable exception){
        super(exception);
    }
}
