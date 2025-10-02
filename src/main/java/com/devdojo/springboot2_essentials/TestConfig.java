package com.devdojo.springboot2_essentials;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AnimeRepository animeRepository;

    @Override
    public void run(String... args) throws Exception {

        Anime anime = new Anime(null, "Maça");
        Anime a1 = new Anime(null,"Banana");
        Anime a2 = new Anime(null,"Uva");
        Anime a3 = new Anime(null,"Morango");
        Anime a4 = new Anime(null,"Abacaxi");


        animeRepository.saveAll(Arrays.asList(anime, a1, a2, a3, a4));
    }
}
