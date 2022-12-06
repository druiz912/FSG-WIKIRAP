package com.druiz.fullstack.wikirap.album.domain;

import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.domain.Category;
import com.druiz.fullstack.wikirap.song.domain.Song;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongInputDto;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import com.druiz.fullstack.wikirap.utils.exceptions.UnprocessableException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlbum;

    /* Un álbum puede tener N artistas relacionados */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_artist")
    @JsonBackReference
    private Artist artists;

    // TODO: CONTINUAR CON LA RELACIÓN DE LAS TABLAS ->

    /* Un álbum tiene n canciones */
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Song> songs;

    /*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private List<Category> category;
*/
    @Column(name = "portada_url")
    private String titlePage;

    @Column(name = "titulo")
    private String title;
    @Column(name = "duracion")
    private String duration;
    @Column(name = "fecha_estreno")
    private String departureDate;

    public void update(Album album) {
        try {
            artists = album.getArtists();
            titlePage = album.getTitlePage();
            title = album.getTitle();
            duration = album.getDuration();
            departureDate = album.getDepartureDate();
        } catch (Exception e) {
            throw new NotFoundException("No se ha podido actualizar el album");
        }
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }


}


