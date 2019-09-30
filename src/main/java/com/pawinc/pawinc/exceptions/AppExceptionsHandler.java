package com.pawinc.pawinc.exceptions;

import com.pawinc.pawinc.ui.model.response.ErrorMesssage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyExceptions(Exception ex, WebRequest request){

        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null) errorMessageDescription = ex.toString();

        ErrorMesssage errorMesssage = new ErrorMesssage(new Date(), errorMessageDescription);

        return new ResponseEntity<>(
                errorMesssage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = {NullPointerException.class, CenterServiceException.class, AnimalServiceException.class})
    public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest request){

        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null) errorMessageDescription = ex.toString();

        ErrorMesssage errorMesssage = new ErrorMesssage(new Date(), errorMessageDescription);

        return new ResponseEntity<>(
                errorMesssage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
