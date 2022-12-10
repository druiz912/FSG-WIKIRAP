package com.druiz.fullstack.wikirap.album.application;

import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;

import java.util.List;

public interface AlbumService {

    AlbumOutputDto addAlbum(AlbumInputDto albumInputDto);

    AlbumOutputDto updateAlbum(int id, AlbumInputDto albumInputDto);

    List<AlbumOutputDto> addListAlbums (List<AlbumInputDto> albumInputDtoList);

    AlbumOutputDto findAlbumById (int id);

    List<AlbumOutputDto> findAlbumByTitle(String title);

    void deleteAlbum(int id);

    List<AlbumOutputDto> findAllAlbum();

    List<AlbumOutputDto> findAlbumByArtist(String name);

}
