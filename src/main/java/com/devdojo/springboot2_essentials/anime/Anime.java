package com.devdojo.springboot2_essentials.anime;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Anime implements Serializable {

    private Long id;
    private String name;

}
