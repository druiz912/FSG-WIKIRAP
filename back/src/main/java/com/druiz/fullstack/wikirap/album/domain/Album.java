package com.druiz.fullstack.wikirap.album.domain;

import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.domain.Song;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Album")
@Table(name="Album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_artist",referencedColumnName = "id")
    private Artist artist;

    @OneToMany(targetEntity = Song.class, cascade = CascadeType.ALL)
    private List<Song> songs;

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


