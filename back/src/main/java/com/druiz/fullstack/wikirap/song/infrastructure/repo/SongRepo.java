package com.druiz.fullstack.wikirap.song.infrastructure.repo;

import com.druiz.fullstack.wikirap.song.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepo extends JpaRepository<Song, Integer> {
    List<Song> findSongByTitle(String title);
}
