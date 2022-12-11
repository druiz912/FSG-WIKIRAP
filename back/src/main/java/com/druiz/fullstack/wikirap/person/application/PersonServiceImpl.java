package com.druiz.fullstack.wikirap.person.application;

import com.druiz.fullstack.wikirap.person.application.port.PersonService;
import com.druiz.fullstack.wikirap.person.domain.Person;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonInputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonOutputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.repo.PersonRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepo personRepo;



    /**
     * MÉTODO PARA AÑADIR UNA PERSONA RECIBIENDO POR PARÁMETROS UN DTO
     **/
    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) {
        /* Inicializamos variable person tipo Person y le pasamos input */
        Person person = new Person(personInputDto);
        /* */
        personRepo.save(person);
        /* */
        return new PersonOutputDto(person);
    }

    /**
     * MÉTODO PARA ACTUALIZAR UNA PERSONA RECIBIENDO POR PARÁMETROS UN DTO Y UN ID
     **/
    @Override
    public PersonOutputDto updatePerson(int id, PersonInputDto personInputDto) {
        /* Buscando el ID en las personas que tenemos en la BB DD */
        Person personInDB = personRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Person with id: " + id + " not found"));
        /* Haciendo uso de una función creada para actualizar  */
        personInDB.update(personInputDto);
        /* */
        personRepo.save(personInDB);
        return new PersonOutputDto(personInDB);
    }

    @Override
    public String deletePerson(int id) {
        if (personRepo.findById(id).isPresent()) {
            personRepo.deleteById(id);
            return "The person with id " + id + " is deleted";
        } else {
            throw new NotFoundException("Person with id " + id + " has not been found, so it cannot be deleted");
        }
    }

    /**
     * MÉTODO PARA AÑADIR UNA LISTA DE PERSONAS
     **/
    @Override
    public List<PersonOutputDto> addListPersons(List<PersonInputDto> personInputDtoList) {
        List<Person> personList = new ArrayList<>();
        List<PersonOutputDto> listPersonOutput = new ArrayList<>();
        /* PIPELINE */
        personInputDtoList.forEach(p -> {
            Person person = new Person(p);
            personList.add(person);
        });
        // Save
        personRepo.saveAll(personList);
        // Convertir a OutputDto
        personList.forEach(entity -> {
            PersonOutputDto outputDto = new PersonOutputDto(entity);
            listPersonOutput.add(outputDto);
        });
        return listPersonOutput;
    }


    /**
     * MÉTODOS DE BÚSQUEDA:
     * 1. Buscar persona por ID
     * 2. Buscar persona por Name
     * 3. Buscar todas las personas
     **/

    // 1
    @Override
    public PersonOutputDto findPersonById(int id) {
        // 1. Buscamos tal id
        Person person = personRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Person with id " + id + " not found"));
        // 2. Mapeamos la entidad a Output
        return new PersonOutputDto(person);
    }

    // 2
    @Override
    public List<PersonOutputDto> findPersonByName(String name) {
        List<Person> personList = personRepo.findPersonByName(name);
        return mapListEntityToDto(personList);
    }

    // 3
    @Override
    public List<PersonOutputDto> findAllPersons() {
        List<Person> personList = personRepo.findAll();
        return mapListEntityToDto(personList);
    }

    // FUNCIONES AUXILIARES

    protected List<PersonOutputDto> mapListEntityToDto(List<Person> lista){
        List<PersonOutputDto> list = new ArrayList<>();
        // Iteración sobre la lista para mapear cada elemento y añadirlo a la lista
        lista.forEach(entity -> {
            PersonOutputDto outputDto = new PersonOutputDto(entity);
            list.add(outputDto);
        });
        return list;
    }
}
