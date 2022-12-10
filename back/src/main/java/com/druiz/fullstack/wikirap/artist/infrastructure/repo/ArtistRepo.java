package com.druiz.fullstack.wikirap.artist.infrastructure.repo;

import com.druiz.fullstack.wikirap.artist.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistRepo extends JpaRepository<Artist, Integer> {

    /**
     *
     * @param idPerson
     * @return Artist
     */
    @Query(value = "SELECT * FROM artist AS art WHERE art.id_person= ?", nativeQuery = true)
    Artist getPersonQuery(Integer idPerson);

    List<Artist> findByApodo(String apodo);
}
