package com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistInputDto {

    private int idPerson;

    private String apodo;

    private String imageUrl;

    private String occupation;

    private String periodoActivo;

    private String details;


}
