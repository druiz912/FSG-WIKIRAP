package com.druiz.fullstack.wikirap.song.infrastructure.controller.dto;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.song.domain.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongOutputDto {

    private int idSong;
    private String title;
    private LocalTime duration;
    private String description;
    private LocalDate departureDate;
    private Album idAlbum;

    public SongOutputDto(Song song) {
        idSong = song.getIdSong();
        title = song.getTitle();
        duration = song.getDuration();
        description = song.getDescription();
        departureDate = song.getDepartureDate();
        idAlbum = song.getAlbum();
    }
}
