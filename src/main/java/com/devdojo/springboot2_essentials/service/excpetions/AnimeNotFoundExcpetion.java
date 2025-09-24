package com.devdojo.springboot2_essentials.service.excpetions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimeNotFoundExcpetion extends RuntimeException {
    public AnimeNotFoundExcpetion(String message) {
        super(message);
    }
}
