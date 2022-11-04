package com.druiz.fullstack.wikirap.utils.mapper;

import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    // Mapeo para convertir InputDto a Entity
    Artist mapInputToEntity(ArtistInputDto artistInputDto);
    // Mapeo para convertir Entity a OutputDto
    ArtistOutputDto mapEntityToOutput(Artist artist);

    List<ArtistOutputDto> mapListEntityToOutput(List<Artist> artistList);

}
