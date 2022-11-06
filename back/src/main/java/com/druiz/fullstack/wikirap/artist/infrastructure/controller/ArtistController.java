package com.druiz.fullstack.wikirap.artist.infrastructure.controller;

import com.druiz.fullstack.wikirap.artist.application.port.ArtistService;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    /** ------------ ** POST ** --------------- **/

    // Añadir 1 artista
    @PostMapping
    public ArtistOutputDto addArtist(@RequestBody ArtistInputDto artistInputDto){
        return artistService.addArtist(artistInputDto);
    }

    // Añadir una lista de artistas
    @PostMapping("add/list")
    public List<ArtistOutputDto> addListArtists(@RequestBody List<ArtistInputDto> artistInputDtoList){
        return artistService.addListArtists(artistInputDtoList);
    }

    /** ------------ ** GET ** --------------- **/

    // Obtener una lista de artistas
    @GetMapping
    public List<ArtistOutputDto> getAllArtists(){
        return artistService.findAllArtists();
    }

    // Obtener una lista de artistas según el apodo
    @GetMapping("aka/{apodo}")
    public List<ArtistOutputDto> getArtistByApodo(@PathVariable String apodo){
        return artistService.findArtistByApodo(apodo);
    }

    // Obtener artista según su id
    @GetMapping("id/{id}")
    public ArtistOutputDto getArtistById(@PathVariable int id){
        return artistService.findArtistById(id);
    }


    /** ------------ ** PUT & DELETE ** --------------- **/
    @PutMapping("id/{id}")
    public ArtistOutputDto updateArtist(@PathVariable int id,@RequestBody ArtistInputDto artistInputDto){
        return artistService.updateArtist(id, artistInputDto);
    }

    @DeleteMapping("id/{id}")
    public String deletePerson(@PathVariable int id){
        return artistService.deleteArtist(id);
    }




}