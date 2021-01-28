package com.example.paymentservice.exception;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredFeign(FeignException e) {
        return new ResponseEntity<>(new ExceptionEntity(new String(e.content())), HttpStatus.resolve(e.status()));
    }
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ExceptionEntity> handleEntity(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionEntity(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ExceptionEntity> handleDatabaseFindException(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ExceptionEntity(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Data
    @AllArgsConstructor
    static class ExceptionEntity {
        private String message;
    }

    public static String createExceptionMessage(List<ObjectError> errors){
        StringBuilder builder = new StringBuilder();
        errors.forEach((error)->{
            FieldError err = (FieldError) error;
            builder.append("Field - ")
                    .append(err.getField())
                    .append(" : ")
                    .append(err.getDefaultMessage())
                    .append(System.getProperty("line.separator"));
        });
        return builder.toString();
    }
}
