package com.druiz.fullstack.wikirap.album.domain;

import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.song.domain.Song;
import lombok.*;

import javax.persistence.*;
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

    /* Un álbum puede tener varios artistas relacionados */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_id_artist")
    private Artist artist;

    /* Un álbum tiene varias canciones */
    @OneToMany(targetEntity = Song.class, cascade = CascadeType.REFRESH)
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


