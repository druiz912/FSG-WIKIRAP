package com.druiz.fullstack.wikirap.artist.infrastructure.repo;

import com.druiz.fullstack.wikirap.artist.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepo extends JpaRepository<Artist, Integer> {

    List<Artist> findByApodo(String apodo);
}
