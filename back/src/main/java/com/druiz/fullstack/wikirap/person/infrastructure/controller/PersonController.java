package com.druiz.fullstack.wikirap.person.infrastructure.controller;

import com.druiz.fullstack.wikirap.person.application.port.PersonService;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonInputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonOutputDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /** ----------- ** POST ** --------------- **/

    // Añadir una persona
    @PostMapping
    public PersonOutputDto addPerson(@RequestBody PersonInputDto personInputDto) {
        return personService.addPerson(personInputDto);
    }
    // Añadir una lista de personas
    @PostMapping("add/list")
    public List<PersonOutputDto> addListPerson(@RequestBody List<PersonInputDto> personInputDtoList){
        return personService.addListPersons(personInputDtoList);
    }

    /** ------------ ** GET ** --------------- **/

    // Obtener una lista con todas las personas
    @GetMapping
    public List<PersonOutputDto> getAllPersons() {
        return personService.findAllPersons();
    }
    // Obtener una lista de todas las personas según el nombre
    @GetMapping("/name/{name}")
    public List<PersonOutputDto> getAllPersonByName(@PathVariable String name) {
        return personService.findPersonByName(name);
    }
    // Obtener una persona según el ID
    @GetMapping("id/{id}")
    public PersonOutputDto getPersonById(@PathVariable int id){
        return personService.findPersonById(id);
    }

    /** ------------ ** PUT & DELETE ** --------------- **/

    @PutMapping("id/{id}")
    public PersonOutputDto updatePerson(@PathVariable int id, @RequestBody PersonInputDto personInputDto){
        return personService.updatePerson(id, personInputDto);
    }

    @DeleteMapping("id/{id}")
    public String deletePerson(@PathVariable int id){
        return personService.deletePerson(id);
    }
}
