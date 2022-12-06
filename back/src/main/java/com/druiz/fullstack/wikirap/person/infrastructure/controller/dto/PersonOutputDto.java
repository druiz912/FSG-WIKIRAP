package com.druiz.fullstack.wikirap.person.infrastructure.controller.dto;

import com.druiz.fullstack.wikirap.person.domain.Person;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutputDto {

    private int idPerson;
    private String name;
    private String surnames;
    private int age;
    private LocalDate dateOfBirth;
    private String origen;
    private Float altura;
    private String occupation;

    public PersonOutputDto(Person person) {
        idPerson = person.getIdPerson();
        name = person.getName();
        surnames = person.getSurnames();
        age = person.getAge();
        dateOfBirth = person.getDateOfBirth();
        origen = person.getOrigen();
        altura = person.getAltura();
        occupation = person.getOccupation();
    }
}
