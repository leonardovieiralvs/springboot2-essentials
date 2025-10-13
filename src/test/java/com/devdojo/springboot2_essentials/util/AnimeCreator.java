package com.devdojo.springboot2_essentials.util;

import com.devdojo.springboot2_essentials.anime.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Attack on Titan")
                .build();
    }

    public static Anime createValidAnime() {
        return Anime.builder()
                .name("Attack on Titan")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
                .name("Attack on Titan 2")
                .id(1L)
                .build();
    }
}
