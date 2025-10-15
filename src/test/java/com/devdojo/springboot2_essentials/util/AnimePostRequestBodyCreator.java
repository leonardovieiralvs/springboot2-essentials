package com.devdojo.springboot2_essentials.util;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {

    public static AnimePostRequestBody animePostRequestBody() {
        return AnimePostRequestBody.builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();
    }
}
