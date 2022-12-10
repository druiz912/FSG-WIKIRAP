package com.druiz.fullstack.wikirap.utils.mapper;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    // Mapeo para convertir InputDto a Entity
    /*Album mapInputToEntity(AlbumInputDto albumInputDto);
    // Mapeo para convertir Entity a OutputDto
     AlbumOutputDto mapEntityToOutput(Album album);

    List<AlbumOutputDto> mapListEntityToOutput(List<Album> albums);

*/

}
