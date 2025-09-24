package com.devdojo.springboot2_essentials.service;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.repository.AnimeRepository;
import com.devdojo.springboot2_essentials.requests.AnimePostRequest;
import com.devdojo.springboot2_essentials.requests.AnimePutRequest;
import com.devdojo.springboot2_essentials.service.excpetions.AnimeNotFoundExcpetion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequest(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new AnimeNotFoundExcpetion("Anime not found"));
    }

    public Anime save(AnimePostRequest animePostRequest) {
        return animeRepository.save(Anime.builder().name(animePostRequest.getName()).build());
    }

    public void deleteById(Long id) {
        animeRepository.delete(findByIdOrThrowBadRequest(id));
    }

    public Anime update(AnimePutRequest animePutRequest) {

        Anime savedAnime = findByIdOrThrowBadRequest(animePutRequest.getId());
        return animeRepository.save(Anime.builder()
                .id(savedAnime.getId())
                .name(animePutRequest.getName())
                .build());
    }
}
