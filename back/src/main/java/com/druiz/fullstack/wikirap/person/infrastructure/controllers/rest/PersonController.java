package com.druiz.fullstack.wikirap.person.infrastructure.controllers.rest;

import com.druiz.fullstack.wikirap.person.application.port.PersonService;
import com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto.PersonInputDto;
import com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto.PersonOutputDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /** MÉTODOS POST
     * 1. Añadir una persona
     * 2. Añadir varias personas a la vez
     * **/

    // ** 1 **
    @PostMapping
    public PersonOutputDto addPerson(PersonInputDto personInputDto) {
        return personService.addPerson(personInputDto);
    }
    // ** 2 **
    @PostMapping("list")
    public List<PersonOutputDto> addListPerson(List<PersonInputDto> personInputDtoList){
        return personService.addListPersons(personInputDtoList);
    }

    /** MÉTODO GET
     * 1. Obtener todas las personas
     * 2. Obtener todas las personas según su nombre
     * 3. Obtener persona según su ID
     * **/

    // ** 1 **
    @GetMapping
    public List<PersonOutputDto> getAllPersons() {
        return personService.findAllPersons();
    }
    // ** 2 **
    @GetMapping("/name/{name}")
    public List<PersonOutputDto> getAllPersonByName(@PathVariable String name) {
        return personService.findPersonByName(name);
    }
    // ** 3 **
    @GetMapping("id/{id}")
    public PersonOutputDto getPersonById(@PathVariable int id){
        return personService.findPersonById(id);
    }

    /* ****** */

    /** MÉTODO PUT **/
    @PutMapping("id/{id}")
    public PersonOutputDto updatePerson(@PathVariable int id, @RequestBody PersonInputDto personInputDto){
        return personService.updatePerson(id, personInputDto);
    }

    /** MÉTODO DELETE **/
    @DeleteMapping("id/{id}")
    public String deletePerson(@PathVariable int id){
        return personService.deletePerson(id);
    }
}
