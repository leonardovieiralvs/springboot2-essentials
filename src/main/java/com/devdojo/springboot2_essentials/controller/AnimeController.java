package com.devdojo.springboot2_essentials.controller;

import com.devdojo.springboot2_essentials.anime.Anime;
import com.devdojo.springboot2_essentials.requests.AnimePostRequestBody;
import com.devdojo.springboot2_essentials.requests.AnimePutRequestBody;
import com.devdojo.springboot2_essentials.service.AnimeService;
import com.devdojo.springboot2_essentials.util.DateUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/animes")
@Log4j2
@AllArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping("/pages")
    public ResponseEntity<Page<Anime>> listPageable(Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listPageable(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anime>> listAllAnimes() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAllAnimes());
    }

    @GetMapping("{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id) {
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequest(id));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> create(@RequestBody @Valid AnimePostRequestBody animePostRequest) {
        Anime obj = animeService.save(animePostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        animeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> updateById(@RequestBody AnimePutRequestBody animePutRequest) {
        animeService.update(animePutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
