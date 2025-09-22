package com.devdojo.springboot2_essentials.service;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.repository.AnimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {

    private List<Anime> animes = List.of(new Anime(1L,"DBZ"), new Anime(2L,"Berserker"));


    public List<Anime> listAll() {
        return animes;
    }

    public Anime findById(Long id) {
        return animes.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }
}
