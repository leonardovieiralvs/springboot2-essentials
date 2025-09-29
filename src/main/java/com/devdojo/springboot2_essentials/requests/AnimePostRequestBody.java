package com.devdojo.springboot2_essentials.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "request one name")
    private String name;

}
