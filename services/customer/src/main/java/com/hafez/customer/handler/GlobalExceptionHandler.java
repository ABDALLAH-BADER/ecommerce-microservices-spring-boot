package com.hafez.customer.handler;
import com.hafez.customer.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exp){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException (MethodArgumentNotValidException exp){
         var errors = new HashMap<String,String>();
         exp.getBindingResult().getAllErrors()
                 .forEach( error ->{
                     var fieldName = ((FieldError)error).getField();
                     var errorMassage = error.getDefaultMessage();
                     errors.put(fieldName,errorMassage);
                 });
         return ResponseEntity
                 .status(BAD_REQUEST)
                 .body(new ErrorResponse(errors));
    }

}
