package com.druiz.fullstack.wikirap.album.infrastructure.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumInputDto {

    @NotNull
    private int idArtist;
    @NotNull
    private String urlPortada;
    @NotNull
    private String title;
    @NotNull
    private String duration;
    @NotNull
    private String fechaSalida;

}
