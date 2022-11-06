package com.druiz.fullstack.wikirap.song.application.port;

import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongInputDto;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongOutputDto;

import java.util.List;

public interface SongService {
    SongOutputDto addSong(SongInputDto songInputDto);

    List<SongOutputDto> addListSongs(List<SongInputDto> songInputDtoList);

    List<SongOutputDto> findAllSongs();

    List<SongOutputDto> findSongByTitle(String title);

    SongOutputDto updateSong(int id, SongInputDto songInputDto);

    String deleteSong(int id);

    SongOutputDto findSongById(int id);
}
