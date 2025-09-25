package com.devdojo.springboot2_essentials.mapper;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.requests.AnimePostRequestBody;
import com.devdojo.springboot2_essentials.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequest);
    public abstract Anime toAnime(AnimePutRequestBody animePutRequest);

}

