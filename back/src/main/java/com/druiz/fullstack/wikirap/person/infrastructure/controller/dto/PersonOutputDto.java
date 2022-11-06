package com.druiz.fullstack.wikirap.person.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutputDto {

    private int idPerson;
    private String name;
    private String surname;
    private String surname1;
    private int age;
    private String dateOfBirth;
    private String origen;
    private Float altura;
    private String occupation;

}
