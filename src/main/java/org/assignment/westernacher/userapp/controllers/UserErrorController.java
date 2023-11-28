package org.assignment.westernacher.userapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserErrorController {

    @ExceptionHandler
    ResponseEntity handleJPAViolations(TransactionSystemException exception){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler
    ResponseEntity handleNotFoundException(NotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    ResponseEntity handleIncorrectDate(DateTimeParseException exception){
        return ResponseEntity.badRequest().body("Invalid date format");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){

        List errorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String > errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorList);
    }
}