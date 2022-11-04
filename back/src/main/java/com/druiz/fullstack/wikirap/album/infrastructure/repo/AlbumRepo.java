package com.druiz.fullstack.wikirap.album.infrastructure.repo;


import com.druiz.fullstack.wikirap.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AlbumRepo extends JpaRepository<Album, Integer> {

    // TODO: Revisar esto, porque hay que hacer un join ya que tengo que consultar la tabla de Artistas

    @Query(nativeQuery = true, value = "SELECT * FROM Albums WHERE artist like %:artist%")
    List<Album> findByArtist(String artist);

    @Query(nativeQuery = true, value = "SELECT * FROM Albums WHERE titulo like %:titulo%")
    List<Album> findByTitle(String titulo);

    @Query(nativeQuery = true, value = "SELECT * FROM Albums limit :initial,:offset")
    List<Album> getAlbumRange(int initial, int offset);

    @Query(nativeQuery = true,value="SELECT COUNT(*) from Albums")
    Integer getNumberOfAlbums();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="DELETE FROM Albums where id>101")
    void clear();
}
