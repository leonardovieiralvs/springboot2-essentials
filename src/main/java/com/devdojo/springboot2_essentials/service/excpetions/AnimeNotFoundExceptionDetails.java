package com.devdojo.springboot2_essentials.service.excpetions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AnimeNotFoundExceptionDetails {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;

}
