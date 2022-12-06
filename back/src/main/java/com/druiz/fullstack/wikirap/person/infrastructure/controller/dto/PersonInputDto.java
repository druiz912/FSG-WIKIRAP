package com.druiz.fullstack.wikirap.person.infrastructure.controller.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInputDto {

    @NotEmpty(message = "The name of the person is required")
    private String name;
    private String surnames;
    @NotEmpty(message = "The date of birth is required")
    private LocalDate dateOfBirth;
    private String origen;
    private Float altura;
    private String occupation;

}
