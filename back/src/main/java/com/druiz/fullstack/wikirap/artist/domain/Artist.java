package com.druiz.fullstack.wikirap.artist.domain;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.person.domain.Person;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificador")
    private int id;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @Column(name = "apodo", length = 100)
    private String apodo;
    @Column(name = "url_imagen")
    private String imageUrl;
    @Column(name = "periodo_activo")
    private String periodoActivo;
    @Column(name = "detalles")
    private String details;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "artist",
            cascade = CascadeType.ALL)
    List<Album> albums;

    public Artist(String apodo, String imageUrl, String periodoActivo, String details) {
        this.apodo = apodo;
        this.imageUrl = imageUrl;
        this.periodoActivo = periodoActivo;
        this.details = details;
    }

    public void update(ArtistInputDto artistInputDto) {
        apodo = artistInputDto.getApodo();
        imageUrl = artistInputDto.getImageUrl();
        periodoActivo = artistInputDto.getPeriodoActivo();
        details = artistInputDto.getDetails();
        // TODO: List<Albums> && Person
    }

}
