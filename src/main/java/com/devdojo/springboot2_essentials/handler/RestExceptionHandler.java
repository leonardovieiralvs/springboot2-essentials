package com.devdojo.springboot2_essentials.handler;


import com.devdojo.springboot2_essentials.service.excpetions.AnimeNotFoundException;
import com.devdojo.springboot2_essentials.service.excpetions.AnimeNotFoundExceptionDetails;
import com.devdojo.springboot2_essentials.service.excpetions.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(AnimeNotFoundException.class)
    public ResponseEntity<AnimeNotFoundExceptionDetails> handlerNotFoundException(AnimeNotFoundException obj) {
        return new ResponseEntity<>(AnimeNotFoundExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not found.")
                .message("Anime not found, by lelo")
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        return new ResponseEntity<>(ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not found.")
                .message("Anime not found, by lelo")
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build(), HttpStatus.NOT_FOUND);
    }
}
