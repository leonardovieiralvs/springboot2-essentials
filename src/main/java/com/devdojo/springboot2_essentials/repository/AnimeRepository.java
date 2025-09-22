package com.devdojo.springboot2_essentials.repository;

import com.devdojo.springboot2_essentials.anime.Anime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository {

    public List<Anime> listAll();
}
