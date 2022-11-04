package com.druiz.fullstack.wikirap.person.application;

import com.druiz.fullstack.wikirap.person.application.port.PersonService;
import com.druiz.fullstack.wikirap.person.domain.Person;
import com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto.PersonInputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto.PersonOutputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.repo.PersonRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import com.druiz.fullstack.wikirap.utils.mapper.PersonMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepo personRepo;
    private PersonMapper mapper;

    /** INYECCIÓN POR CONSTRUCTOR **/
    public PersonServiceImpl(PersonRepo personRepo, PersonMapper mapper) {
        this.personRepo = personRepo;
        this.mapper = mapper;
    }

    /** MÉTODO PARA AÑADIR UNA PERSONA RECIBIENDO POR PARÁMETROS UN DTO **/
    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) {
        /* Haciendo uso de MapStruct */
        Person personMap = mapper.mapInputToEntity(personInputDto);
        //
        Person person = personRepo.save(personMap);
        return mapper.mapEntityToOutput(person);
    }

    /** MÉTODO PARA ACTUALIZAR UNA PERSONA RECIBIENDO POR PARÁMETROS UN DTO Y UN ID **/
    @Override
    public PersonOutputDto updatePerson(int id, PersonInputDto personInputDto) {
        /* Buscando el ID en las personas que tenemos en la BB DD */
        Person personInDB = personRepo.findById(id).orElseThrow(
                ()-> new NotFoundException("Person with id: " + id + " not found"));
        /* Haciendo uso de una función creada para actualizar  */
        personInDB.update(personInputDto);
        personRepo.save(personInDB);
        return mapper.mapEntityToOutput(personInDB);
    }

    @Override
    public String deletePerson(int id) {
        if(personRepo.findById(id).isPresent()){
            personRepo.deleteById(id);
            return "The person with id " + id + " is deleted";
        } else {
            throw new NotFoundException("Person with id " + id + " has not been found, so it cannot be deleted");
        }
    }

    /** MÉTODO PARA AÑADIR UNA LISTA DE PERSONAS **/
    @Override
    public List<PersonOutputDto> addListPersons(List<PersonInputDto> personInputDtoList) {
        List<Person> personList = new ArrayList<>();
        /* PIPELINE */
        personInputDtoList.forEach( p -> {
           Person person = mapper.mapInputToEntity(p);
           personList.add(person);
        });
        // Save
        personRepo.saveAll(personList);
        // Convertir a OutputDto
        return mapper.mapListEntityToOutput(personList);
    }


    /** MÉTODOS DE BÚSQUEDA:
     *  1. Buscar persona por ID
     *  2. Buscar persona por Name
     *  3. Buscar todas las personas
     * **/

    // 1
    @Override
    public PersonOutputDto findPersonById(int id) {
        // 1. Buscamos tal id
        Person person = personRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Person with id " + id + " not found"));
        // 2. Mapeamos la entidad a Output
        return PersonMapper.INSTANCE.mapEntityToOutput(person);
    }

    // 2
    @Override
    public List<PersonOutputDto> findPersonByName(String name) {
       List<Person> person = personRepo.findByName(name);
       return PersonMapper.INSTANCE.mapListEntityToOutput(person);
    }
    // 3
    @Override
    public List<PersonOutputDto> findAllPersons() {
        List<Person> personList = personRepo.findAll();
        return PersonMapper.INSTANCE.mapListEntityToOutput(personList);
    }
}
