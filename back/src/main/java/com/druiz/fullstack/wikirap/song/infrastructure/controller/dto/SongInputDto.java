package com.druiz.fullstack.wikirap.song.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongInputDto {

    @NotEmpty
    private String title;
    private String duration;
    private String description;
    private String departureDate;
    private int idAlbum;


}
