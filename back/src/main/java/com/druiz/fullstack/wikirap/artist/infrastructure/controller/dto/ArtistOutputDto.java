package com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto;

import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.person.domain.Person;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonOutputDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistOutputDto {

    private int idArtist;

    private String apodo;

    private String periodoActivo;

    private String details;

    private PersonOutputDto person;

    private List<String> titleAlbum;


    public ArtistOutputDto(Artist artist){
        idArtist = artist.getIdArtist();
        apodo = artist.getApodo();
        periodoActivo = artist.getPeriodoActivo();
        details = artist.getDetails();
        person = new PersonOutputDto(artist.getPerson());
    }
}
