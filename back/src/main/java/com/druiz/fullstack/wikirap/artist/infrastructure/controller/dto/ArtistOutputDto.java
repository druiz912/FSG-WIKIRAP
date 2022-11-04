package com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto;

import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
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
