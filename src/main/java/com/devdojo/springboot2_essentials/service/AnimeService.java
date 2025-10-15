package com.devdojo.springboot2_essentials.service;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.mapper.AnimeMapper;
import com.devdojo.springboot2_essentials.repository.AnimeRepository;
import com.devdojo.springboot2_essentials.requests.AnimePostRequestBody;
import com.devdojo.springboot2_essentials.requests.AnimePutRequestBody;
import com.devdojo.springboot2_essentials.service.excpetions.AnimeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listPageable(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllAnimes() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequest(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new AnimeNotFoundException("Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void deleteById(Long id) {
        animeRepository.delete(findByIdOrThrowBadRequest(id));
    }

    public Anime replace(AnimePutRequestBody animePutRequestBody) {
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        return animeRepository.save(anime);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }
}
