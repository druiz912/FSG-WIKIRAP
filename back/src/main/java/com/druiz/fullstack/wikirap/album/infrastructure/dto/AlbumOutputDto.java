package com.druiz.fullstack.wikirap.album.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumOutputDto {

    private int idAlbum;

    private int idArtist;

    private String portada;

    private String title;

    private String duration;

    private String fechaSalida;


}
