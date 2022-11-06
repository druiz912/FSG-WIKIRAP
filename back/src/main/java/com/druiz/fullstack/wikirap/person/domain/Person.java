package com.druiz.fullstack.wikirap.person.domain;

import com.druiz.fullstack.wikirap.person.infrastructure.controller.dto.PersonInputDto;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerson;
    @Column(name = "nombre", length = 80)
    private String name;
    @Column(name = "primer_apellido", length = 50)
    private String surname;
    @Column(name = "segundo_apellido",length = 50)
    private String surname1;
    @Column(name = "edad",length = 3, nullable = false)
    private int age;
    @Column(name = "fecha_nacimiento")
    private String dateOfBirth;
    @Column(name = "origen", length = 120)
    private String origen;
    @Column(name = "altura")
    private Float altura;
    @Column(name = "ocupacion", length = 120)
    private String occupation;

    /** Método VOID que actualiza los atributos de Person recibiendo por parámetros un InputDTO **/
    public void update(PersonInputDto input) {
        name = input.getName();
        surname = input.getSurname();
        surname1 = input.getSurname1();
        age = input.getAge();
        dateOfBirth = input.getDateOfBirth();
        origen = input.getOrigen();
        altura = input.getAltura();
        occupation = input.getOccupation();
    }



}
