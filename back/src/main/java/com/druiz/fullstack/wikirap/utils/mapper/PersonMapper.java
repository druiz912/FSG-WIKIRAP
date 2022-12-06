package com.druiz.fullstack.wikirap.utils.mapper;


import com.druiz.fullstack.wikirap.person.domain.Person;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonInputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {


    // Mapeo para convertir InputDto a
    @Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    Person mapInputToEntity(PersonInputDto personInputDto);
    // Mapeo para convertir Entity a OutputDto
    PersonOutputDto mapEntityToOutput(Person person);
    // Mapeo para convertir una lista de entity en lista de OutputDto
    List<PersonOutputDto> mapListEntityToOutput(List<Person> person);
}
