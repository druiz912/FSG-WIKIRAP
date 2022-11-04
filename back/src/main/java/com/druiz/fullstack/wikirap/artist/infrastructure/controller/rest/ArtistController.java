package com.druiz.fullstack.wikirap.artist.infrastructure.controller.rest;

import com.druiz.fullstack.wikirap.artist.application.port.ArtistService;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("artist")
public class ArtistController {


    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    /** MÉTODOS POST
     * 1. Añadir un artista
     * 2. Añadir una lista de artistas
     * **/

    // ** 1 **
    @PostMapping
    public ArtistOutputDto addArtist(ArtistInputDto artistInputDto){
        return artistService.addArtist(artistInputDto);
    }

    // ** 2 **
    @PostMapping
    public List<ArtistOutputDto> addListArtists(List<ArtistInputDto> artistInputDtoList){
        return artistService.addListArtists(artistInputDtoList);
    }

    /** MÉTODO GET
     * 1. Obtener todos los artisas
     * 2. Obtener todas las artistas según su apodo
     * 3. Obtener artista según su ID
     * **/

    // ** 1 **
    public List<ArtistOutputDto> getAllArtists(){
        return artistService.findAllArtists();
    }

    // ** 2 **
    public List<ArtistOutputDto> getArtistByApodo(String apodo){
        return artistService.findArtistByApodo(apodo);
    }

    // ** 3 **
    public ArtistOutputDto getArtistById(int id){
        return artistService.findArtistById(id);
    }

    /* ****** */

    /** MÉTODO PUT **/
    public ArtistOutputDto updateArtist(int id, ArtistInputDto artistInputDto){
        return artistService.updateArtist(id, artistInputDto);
    }

    /** MÉTODO DELETE **/
    public String deletePerson(int id){
        return artistService.deleteArtist(id);
    }




}