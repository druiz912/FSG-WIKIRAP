package com.druiz.fullstack.wikirap.artist.domain;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import com.druiz.fullstack.wikirap.person.domain.Person;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArtist;
    @Column(name = "apodo", length = 100)
    private String apodo;
    @Column(name = "url_imagen")
    private String imageUrl;
    @Column(name = "periodo_activo")
    private String periodoActivo;
    @Column(name = "detalles")
    private String details;

    @OneToMany(mappedBy = "artists", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Album> albums = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person")
    private Person person;


    /** CONSTRUCTORES  **/

    public Artist(int idArtist, String apodo, String imageUrl, String periodoActivo, String details, List<Album> albums, Person person) {
        this.idArtist = idArtist;
        this.apodo = apodo;
        this.imageUrl = imageUrl;
        this.periodoActivo = periodoActivo;
        this.details = details;
        this.albums = albums;
        this.person = person;
    }

    public Artist(ArtistInputDto artistInputDto, Person personFind) {
        person = personFind;
        apodo = artistInputDto.getApodo();
        imageUrl = artistInputDto.getImageUrl();
        periodoActivo = artistInputDto.getPeriodoActivo();
        details = artistInputDto.getDetails();
    }

    public Artist(ArtistInputDto dto) {
        apodo = dto.getApodo();
        imageUrl = dto.getImageUrl();
        periodoActivo = dto.getPeriodoActivo();
        details = dto.getDetails();
    }


    public void update(ArtistInputDto artistInputDto) {
        apodo = artistInputDto.getApodo();
        imageUrl = artistInputDto.getImageUrl();
        periodoActivo = artistInputDto.getPeriodoActivo();
        details = artistInputDto.getDetails();
        // TODO: List<Albums> && Person
    }


    public void addAlbum(Album album){
        albums.add(album);
    }
    public void removeAlbum(Album album){
        albums.remove(album);
    }




}




