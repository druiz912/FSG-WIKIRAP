package com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
