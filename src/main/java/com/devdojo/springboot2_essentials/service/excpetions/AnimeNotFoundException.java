package com.devdojo.springboot2_essentials.service.excpetions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimeNotFoundException extends RuntimeException {
    public AnimeNotFoundException(String message) {
        super(message);
    }
}
