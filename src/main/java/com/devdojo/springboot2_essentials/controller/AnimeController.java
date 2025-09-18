package com.devdojo.springboot2_essentials.controller;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/anime")
@Log4j2
public class AnimeController {

    @Autowired
    private DateUtil dateUtil;

    @GetMapping("/list")
    public List<Anime> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
       return List.of(new Anime("DBZ"), new Anime("Berserker"));
    }

    @GetMapping("/test")
    public String test() {
        return "Funcionando";
    }
}
