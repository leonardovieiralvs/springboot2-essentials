package com.devdojo.springboot2_essentials.service.excpetions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {

    protected LocalDateTime timestamp;
    protected Integer status;
    protected String error;
    protected String message;

}
