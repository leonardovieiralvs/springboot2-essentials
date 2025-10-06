package com.devdojo.springboot2_essentials.client;

import com.devdojo.springboot2_essentials.anime.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        String url = "https://localhost:/8080/animes/2";


        ResponseEntity<Anime> forEntity = new RestTemplate().getForEntity(url, Anime.class);
        log.info(forEntity);

        Anime forObject = new RestTemplate().getForObject(url, Anime.class);
        log.info(forObject);
    }
}
