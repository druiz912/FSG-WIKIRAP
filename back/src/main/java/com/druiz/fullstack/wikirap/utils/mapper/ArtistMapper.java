package com.druiz.fullstack.wikirap.utils.mapper;

import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import com.druiz.fullstack.wikirap.person.domain.Person;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        imports = {Person.class})
public interface ArtistMapper {
/*

unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
    @Mappings({
            @Mapping(target = "artist.albums", ignore = true),
            @Mapping(target = "artist.idArtist", ignore = true),
            @Mapping(target = "person.name", ignore = true),
            @Mapping(target = "person.surnames", ignore = true),
            @Mapping(target = "person.age", ignore = true),
            @Mapping(target = "person.dateOfBirth", ignore = true),
            @Mapping(target = "person.origen", ignore = true),
            @Mapping(target = "person.altura", ignore = true),
            @Mapping(target = "person.occupation", ignore = true),
            @Mapping(source = "dto.idPerson", target = "person.idPerson")
    })
    Artist mapInputToEntityForCreate(ArtistInputDto dto, PersonOutputDto person);
*/
    // Mapeo para convertir InputDto a Entity

    Artist mapInputToEntity(ArtistInputDto dto);
    // Mapeo para convertir Entity a OutputDto

    /*
    @Mapping(target = "albums", ignore = true)
    @Mapping(target = "dateOfBirth", source = "person", dateFormat = "yyyy-MM-dd")
    //TODO : PROBLEMAS AL MAPEAR DATEOFBIRTH MEJOR HACER MAPEO A MANO CREANDO CONSTRUCTOR CON DOS PARAMETROS
    ArtistOutputDto mapEntityToOutput(Artist artist, Person person);

    @Mapping(target = "albums", ignore = true)
    List<ArtistOutputDto> mapListEntityToOutput(List<Artist> artistList);

    ArtistOutputDto mapEntityToOutput(Artist artist);


     */
}
