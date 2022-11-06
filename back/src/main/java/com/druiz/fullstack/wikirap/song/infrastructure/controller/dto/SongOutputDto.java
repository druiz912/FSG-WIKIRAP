package com.druiz.fullstack.wikirap.song.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongOutputDto {

    private int idSong;
    private String title;
    private LocalTime duration;
    private String description;
    private LocalDate departureDate;
    private int idAlbum;
}
