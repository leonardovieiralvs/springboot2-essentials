package com.devdojo.springboot2_essentials.repository;

import com.devdojo.springboot2_essentials.anime.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long>{ }
