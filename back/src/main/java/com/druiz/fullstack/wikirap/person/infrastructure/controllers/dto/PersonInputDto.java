package com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonInputDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String surname1;
    @NotNull
    private int age;
    private LocalDate dateOfBirth;
    private String origen;
    private Float altura;
    private String occupation;

}
