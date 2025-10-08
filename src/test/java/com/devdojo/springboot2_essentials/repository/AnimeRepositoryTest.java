package com.devdojo.springboot2_essentials.repository;

import com.devdojo.springboot2_essentials.anime.Anime;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@Log4j2
@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when Successful")
    void save_CreatesAnime_WhenSuccessful() {
        Anime anime = createdAnime();
        Anime savedAnime = this.animeRepository.save(anime);
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());

    }

    @Test
    @DisplayName("Save updates anime when Successful")
    void save_UpdatesAnime_WhenSuccessful() {
        Anime animeToBeSaved = createdAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
//        log.info(animeSaved.getName());

        animeSaved.setName("Naruto");
        Anime animeUpdated = this.animeRepository.save(animeSaved);
//        log.info(animeUpdated.getName());

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Delete removes anime when Successful")
    void delete_RemovesAnime_WhenSuccessful() {
        Anime animeToBeSaved = createdAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);
        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("FindByName retuns list of Anime when Successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful() {
        Anime animeToBeSaved = createdAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        List<Anime> listAnime = this.animeRepository.findByName(animeSaved.getName());

        Assertions.assertThat(listAnime).isNotEmpty();
        Assertions.assertThat(listAnime).contains(animeSaved);
    }

    @Test
    @DisplayName("FindByName retuns empty list when no Anime is found")
    void findByName_ReturnsEmptyListAnime_WhenAnimeIsNotFound() {
        List<Anime> listAnime = this.animeRepository.findByName("Lvs");

        Assertions.assertThat(listAnime).isEmpty();
    }


    private Anime createdAnime() {
        return Anime.builder().name("Attack on Titan").build();
    }
}