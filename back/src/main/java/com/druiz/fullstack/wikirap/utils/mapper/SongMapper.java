package com.druiz.fullstack.wikirap.utils.mapper;


import com.druiz.fullstack.wikirap.song.domain.Song;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongInputDto;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface SongMapper {

    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    // Mapeo para convertir InputDto a Entity
    Song mapInputToEntity(SongInputDto songInputDto);
    // Mapeo para convertir Entity a OutputDto
    SongOutputDto mapEntityToOutput(Song song);

    List<SongOutputDto> mapListEntityToOutput(List<Song> listSong);
}
