package com.devdojo.springboot2_essentials.handler;


import com.devdojo.springboot2_essentials.service.excpetions.AnimeNotFoundException;
import com.devdojo.springboot2_essentials.service.excpetions.AnimeNotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
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
}
