package com.druiz.fullstack.wikirap.album.infrastructure.repo;

import com.druiz.fullstack.wikirap.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AlbumRepo extends JpaRepository<Album, Integer> {


    @Query(value = "SELECT * FROM album AS al WHERE al.id_artist = ?", nativeQuery = true)
    Album getArtistQuery(int idArtist);

    List<Album> findByTitle(String title);


    @Query(value = "SELECT * FROM album AS al INNER JOIN artist AS art ON al.id_artist = art.id WHERE art.apodo", nativeQuery = true)
    List<Album> findByArtist(String name);
}
