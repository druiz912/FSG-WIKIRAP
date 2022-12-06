package com.druiz.fullstack.wikirap.song.domain;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongInputDto;
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
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSong;

    /* Un álbum tiene varias canciones */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_album")
    private Album album;

    @Column(name = "title")
    private String title;

    @Column(name = "duracion")
    private LocalTime duration;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "fecha_estreno")
    private LocalDate departureDate;

    public void update(SongInputDto input) {
        title = input.getTitle();
        duration = LocalTime.parse(input.getDuration());
        description = input.getDescription();
        departureDate = LocalDate.parse(input.getDepartureDate());
    }
}