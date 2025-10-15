package com.devdojo.springboot2_essentials.controller;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.requests.AnimePostRequestBody;
import com.devdojo.springboot2_essentials.requests.AnimePutRequestBody;
import com.devdojo.springboot2_essentials.service.AnimeService;
import com.devdojo.springboot2_essentials.util.AnimeCreator;
import com.devdojo.springboot2_essentials.util.AnimePostRequestBodyCreator;
import com.devdojo.springboot2_essentials.util.AnimePutRequestBodyCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Log4j2
@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        when(animeServiceMock.listPageable(ArgumentMatchers.any()))
                .thenReturn(animePage);

        when(animeServiceMock.listAllAnimes())
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        when(animeServiceMock.findByIdOrThrowBadRequest(ArgumentMatchers.anyLong()))
                .thenReturn(AnimeCreator.createValidAnime());

        when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        when(animeServiceMock.save(ArgumentMatchers.any(AnimePostRequestBody.class)))
                .thenReturn(AnimeCreator.createValidAnime());

        doNothing().when(animeServiceMock).update(ArgumentMatchers.any(AnimePutRequestBody.class));
    }

    @Test
    @DisplayName("listPageable returns list of anime inside page object when successful")
    void listPageable_ReturnsListOfAnimeInsidePageObject_WhenSuccessful() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeController.listPageable(null).getBody();
        Assertions.assertThat(animePage)
                .isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAllAnime returns list of anime when successful")
    void listAllAnime_ReturnsListOfAnime_WhenSuccessful() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeController.listAllAnimes().getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns anime when successful")
    void findById_ReturnsAnime_WhenSuccessful() {
        Long expectedId = AnimeCreator.createValidAnime().getId();
        Anime animeId = animeController.findById(5L).getBody();

        Assertions.assertThat(animeId.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns anime when successful")
    void findByName_ReturnsAnime_WhenSuccessful() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animeName = animeController.findByName("Variable Test").getBody();

        Assertions.assertThat(animeName)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animeName.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns as empty list of anime when not found")
    void findByName_ReturnsAsEmptyListOfAnime_WhenNotFound() {
        when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> byName = animeController.findByName("Variable Test").getBody();

        Assertions.assertThat(byName)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns anime when successful")
    void save_ReturnsAnime_WhenSuccessful() {
        Anime animeCreated = animeController.create(AnimePostRequestBodyCreator.animePostRequestBody()).getBody();

        Assertions.assertThat(animeCreated)
                .isNotNull()
                .isEqualTo(AnimeCreator.createValidAnime());
    }

    @Test
    @DisplayName("update returns anime when successful")
    void update_ReturnsAnime_WhenSuccessful() {

        Assertions.assertThatCode(() -> animeController.updateById(AnimePutRequestBodyCreator.createAnimePutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = animeController.updateById(AnimePutRequestBodyCreator.createAnimePutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}