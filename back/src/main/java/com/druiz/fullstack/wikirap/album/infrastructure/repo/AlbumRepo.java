package com.druiz.fullstack.wikirap.album.infrastructure.repo;

import com.druiz.fullstack.wikirap.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AlbumRepo extends JpaRepository<Album, Integer> {


    List<Album> findByTitle(String title);
}
