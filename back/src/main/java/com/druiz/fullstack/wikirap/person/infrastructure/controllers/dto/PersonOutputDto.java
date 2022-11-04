package com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutputDto {

    private String name;
    private String surname;
    private String surname1;
    private int age;
    private LocalDate dateOfBirth;
    private String origen;
    private Float altura;
    private String occupation;

    public PersonOutputDto(PersonInputDto personInputDto) {

    }
}
