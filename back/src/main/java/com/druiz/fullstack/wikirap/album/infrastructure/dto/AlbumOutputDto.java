package com.druiz.fullstack.wikirap.album.infrastructure.dto;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonOutputDto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumOutputDto {

    private int idAlbum;

    private String portada;

    private String title;

    private String duration;

    private String fechaSalida;

    private String category;



    public AlbumOutputDto(Album album) {
        if (album == null)
            return;
        this.setIdAlbum(album.getIdAlbum());
        this.setPortada(album.getTitlePage());
        this.setTitle(album.getTitle());
        this.setDuration(album.getDuration());
        this.setFechaSalida(album.getDepartureDate());
    }
}
