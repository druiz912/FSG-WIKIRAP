package com.druiz.fullstack.wikirap.person.domain;

import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonInputDto;
import com.druiz.fullstack.wikirap.utils.exceptions.UnprocessableException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerson;
    @Column(name = "nombre", length = 80)
    private String name;
    @Column(name = "apellidos", length = 50)
    private String surnames;
    @Column(name = "edad", length = 3, nullable = false)
    private int age;
    @Column(name = "fecha_nacimiento")
    private LocalDate dateOfBirth;
    @Column(name = "origen", length = 120)
    private String origen;
    @Column(name = "altura")
    private Float altura;
    @Column(name = "ocupacion", length = 120)
    private String occupation;


    public Person(int idPerson, String name, String surnames, int age, LocalDate dateOfBirth, String origen, Float altura, String occupation) {
        this.idPerson = idPerson;
        this.name = name;
        this.surnames = surnames;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.origen = origen;
        this.altura = altura;
        this.occupation = occupation;
    }

    /** CONSTRUCTOR **/


    public Person(PersonInputDto dto){
        if (dto == null) throw new IllegalArgumentException("Dto cannot be null");
        try {
            name = dto.getName();
            surnames = dto.getSurnames();
            age = calculateAgeByDateOfBirth(dto.getDateOfBirth());
            dateOfBirth = LocalDate.parse(dto.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE));
            origen = dto.getOrigen();
            altura = dto.getAltura();
            occupation = dto.getOccupation();
        } catch (UnprocessableException e) {
            throw new UnprocessableException("No se pudo procesar el mapeado de InputDto a Entidad");
        }
    }

    /** M??TODOS **/
    public int calculateAgeByDateOfBirth(LocalDate dateOfBirth) {
        /* Instanciamos un objeto LocalDate para obtener el tiempo actual */
        LocalDate dateNow = LocalDate.now();
        /* Inicializamos Period para usar su m??todo .between(fecha1, fecha2) */
        Period period = Period.between(dateOfBirth, dateNow);
        /* Devolvemos la variable period pero accedemos al getter de Years */
        return period.getYears();
    }

    public void update(PersonInputDto input) {
        if (input == null) return;
        try {
            name = input.getName();
            surnames = input.getSurnames();
            age = calculateAgeByDateOfBirth(input.getDateOfBirth());
            dateOfBirth = input.getDateOfBirth();
            origen = input.getOrigen();
            altura = input.getAltura();
            occupation = input.getOccupation();
        } catch (UnprocessableException e){
            e.getStackTrace();
        }
    }

}
