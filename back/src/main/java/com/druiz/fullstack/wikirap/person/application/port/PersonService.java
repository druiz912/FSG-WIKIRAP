package com.druiz.fullstack.wikirap.person.application.port;

import com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto.PersonInputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto personInputDto);

    PersonOutputDto updatePerson(int id, PersonInputDto personInputDto);

    List<PersonOutputDto> addListPersons (List<PersonInputDto> personInputDtoList);

    PersonOutputDto findPersonById (int id);

    List<PersonOutputDto> findPersonByName(String name);

    String deletePerson(int id);

    List<PersonOutputDto> findAllPersons();

}
