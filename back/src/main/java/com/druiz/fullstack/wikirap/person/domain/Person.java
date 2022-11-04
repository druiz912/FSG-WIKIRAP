package com.druiz.fullstack.wikirap.person.domain;

import com.druiz.fullstack.wikirap.person.infrastructure.controllers.dto.PersonInputDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificador")
    private int idPerson;
    @Column(name = "nombre", length = 80, nullable = false)
    private String name;
    @Column(name = "primer_apellido", length = 50, nullable = false)
    private String surname;
    @Column(name = "segundo_apellido",length = 50, nullable = false)
    private String surname1;
    @Column(name = "edad",length = 3, nullable = false)
    private int age;
    @Column(name = "fecha_nacimiento")
    private LocalDate dateOfBirth;
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
