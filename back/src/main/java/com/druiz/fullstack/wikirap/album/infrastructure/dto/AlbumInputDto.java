package com.druiz.fullstack.wikirap.album.infrastructure.dto;

import com.sun.istack.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumInputDto {

    @NotNull
    private Integer idArtist;
    
    private int categoryId;
    @NotNull
    private String urlPortada;
    @NotNull
    private String title;
    @NotNull
    private String duration;
    @NotNull
    private String fechaSalida;

}
