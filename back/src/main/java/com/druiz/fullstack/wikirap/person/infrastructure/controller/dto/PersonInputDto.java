package com.druiz.fullstack.wikirap.person.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonInputDto {

    private String name;
    private String surname;
    private String surname1;
    @NotNull
    private int age;
    private String dateOfBirth;
    private String origen;
    private Float altura;
    private String occupation;

}
