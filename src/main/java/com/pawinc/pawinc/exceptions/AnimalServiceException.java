package com.pawinc.pawinc.exceptions;

public class AnimalServiceException extends RuntimeException{

    private static final long serialVersionUID = 249992240257424529L;

    public AnimalServiceException(String message){
        super(message);
    }
}
