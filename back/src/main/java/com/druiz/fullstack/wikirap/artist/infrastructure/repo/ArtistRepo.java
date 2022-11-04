package com.druiz.fullstack.wikirap.artist.infrastructure.repo;

import com.druiz.fullstack.wikirap.artist.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ArtistRepo extends JpaRepository<Artist, Integer> {

    //TODO: findByPeriodoActivo(String periodoActivo)

    @Query(nativeQuery = true, value = "select * from Artistas where name like %:apodo%")
    List<Artist> findByApodo(String apodo);

    @Query(nativeQuery = true, value = "select * from Artistas where origen like %:ocupation%")
    List<Artist> findByOcupation(String ocupation);

    @Query(nativeQuery = true, value = "select * from Artistas limit :initial,:offset")
    List<Artist> getArtistRange (int initial, int offset);

    @Query(nativeQuery = true,value="select count(*) from Artistas")
    Integer getNumberOfArtists();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="delete from Artistas where id>101")
    void clear();
}
