package com.druiz.fullstack.wikirap.album.domain;

import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Songs> songs;

    @Column(name = "portada_url")
    private String titlePage;

    @Column(name = "titulo")
    private String title;
    @Column(name = "duracion")
    private String duration;
    @Column(name = "fecha_estreno")
    private String departureDate;

    public void update(AlbumInputDto albumInputDto) {
        titlePage = albumInputDto.getUrlPortada();
        title = albumInputDto.getTitle();
        duration = albumInputDto.getDuration();
        departureDate = albumInputDto.getFechaSalida();
    }

}


@Entity
@Table(name = "Song")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Songs {

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
    @JoinColumn(name = "id_album")
    private Album album;


}
