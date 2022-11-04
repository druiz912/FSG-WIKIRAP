package com.druiz.fullstack.wikirap.artist.application.port;

import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;

import java.util.List;

public interface ArtistService {

    ArtistOutputDto addArtist(ArtistInputDto artistInputDto);

    ArtistOutputDto updateArtist(int id, ArtistInputDto artistInputDto);

    List<ArtistOutputDto> addListArtists (List<ArtistInputDto> artistInputDtoList);

    ArtistOutputDto findArtistById (int id);

    List<ArtistOutputDto> findArtistByApodo(String name);

    String deleteArtist(int id);

    List<ArtistOutputDto> findAllArtists();

}
