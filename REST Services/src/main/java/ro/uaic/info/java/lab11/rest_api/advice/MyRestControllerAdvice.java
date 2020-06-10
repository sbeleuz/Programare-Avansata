package ro.uaic.info.java.lab11.rest_api.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.uaic.info.java.lab11.rest_api.exceptions.NotFoundException;

@RestControllerAdvice
public class MyRestControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }
}
