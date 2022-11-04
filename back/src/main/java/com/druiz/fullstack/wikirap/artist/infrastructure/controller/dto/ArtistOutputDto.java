package com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto;

import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistOutputDto {

    private int id;

    private int idPerson;

    private String apodo;

    private String occupation;

    private String periodoActivo;

    private String details;

    private List<AlbumOutputDto> albums;


}
