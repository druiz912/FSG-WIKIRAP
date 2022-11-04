package com.druiz.fullstack.wikirap.artist.domain;

import com.druiz.fullstack.wikirap.album.domain.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Song")
@Table(name="Song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "duracion")
    private LocalTime duration;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "fecha_estreno")
    private LocalDate departureDate;

    @ManyToOne
    @JoinColumn(name = "fk_id_album",referencedColumnName  = "id")
    private Album album;
}