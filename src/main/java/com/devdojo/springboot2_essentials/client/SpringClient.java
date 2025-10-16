package com.devdojo.springboot2_essentials.client;

import com.devdojo.springboot2_essentials.anime.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {


        //GET + DETAILS
        ResponseEntity<Anime> forEntity = new RestTemplate().getForEntity("http://localhost:8080/animes/2", Anime.class);
        log.info(forEntity);

        //GET ONLY BODY
        Anime forObject = new RestTemplate().getForObject("http://localhost:8080/animes/2", Anime.class);
        log.info(forObject);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anime>>() {
                });
        log.info(exchange.getBody());


        //POST ONLY BODY
        Anime naruto = Anime.builder().name("Naruto").build();
        Anime narutoSaved = new RestTemplate().postForObject("http://localhost:8080/animes",
                naruto,
                Anime.class);
        log.info("ANIME CREATED ONLY BODY: " + narutoSaved);

        //POST MORE DETAILS
        Anime attackOnTitan = Anime.builder().name("Attack On Titan").build();
        ResponseEntity<Anime> attackOnTitanSaved = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.POST,
                new HttpEntity<>(attackOnTitan),
                Anime.class);
        log.info("ANIME CREATED RESPONSE ENTITY: " + attackOnTitanSaved);


        //PUT
        Anime attackOnTitanUpdate = attackOnTitanSaved.getBody();
        attackOnTitanUpdate.setName("AttackOnTitan 2");

        ResponseEntity<Anime> attackOnTitanUpdate2 = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.PUT,
                new HttpEntity<>(attackOnTitanUpdate),
                Anime.class);

        log.info(attackOnTitanUpdate2);


        //DELETE
        ResponseEntity<Void> animeDelete = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class, attackOnTitanUpdate.getId());

        log.info("Delete ID: " +attackOnTitanUpdate.getId() + animeDelete);

    }
}
