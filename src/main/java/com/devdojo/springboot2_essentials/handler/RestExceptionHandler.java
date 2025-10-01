package com.devdojo.springboot2_essentials.handler;


import com.devdojo.springboot2_essentials.service.excpetions.AnimeNotFoundException;
import com.devdojo.springboot2_essentials.service.excpetions.AnimeNotFoundExceptionDetails;
import com.devdojo.springboot2_essentials.service.excpetions.ExceptionDetails;
import com.devdojo.springboot2_essentials.service.excpetions.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(AnimeNotFoundException.class)
    public ResponseEntity<AnimeNotFoundExceptionDetails> handlerNotFoundException(AnimeNotFoundException obj) {
        return new ResponseEntity<>(AnimeNotFoundExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not found.")
                .message("Anime not found, by lelo")
                .build(), HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleHandlerMethodValidationException(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        return new ResponseEntity<>(ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(exception.getMessage())
                .message(exception.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(statusCode.value())
                .error(ex.getMessage())
                .message("Anime not found, by lelo")
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, statusCode);
    }
}
