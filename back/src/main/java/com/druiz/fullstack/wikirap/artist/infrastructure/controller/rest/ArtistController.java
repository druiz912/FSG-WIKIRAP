package com.druiz.fullstack.wikirap.artist.infrastructure.controller.rest;

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

    /** MÉTODOS POST
     * 1. Añadir un artista
     * 2. Añadir una lista de artistas
     * **/

    // ** 1 **
    @PostMapping
    public ArtistOutputDto addArtist(@RequestBody ArtistInputDto artistInputDto){
        return artistService.addArtist(artistInputDto);
    }

    // ** 2 **
    @PostMapping("/list")
    public List<ArtistOutputDto> addListArtists(@RequestBody List<ArtistInputDto> artistInputDtoList){
        return artistService.addListArtists(artistInputDtoList);
    }

    /** MÉTODO GET
     * 1. Obtener todos los artisas
     * 2. Obtener todas las artistas según su apodo
     * 3. Obtener artista según su ID
     * **/

    // ** 1 **
    @GetMapping
    public List<ArtistOutputDto> getAllArtists(){
        return artistService.findAllArtists();
    }

    // ** 2 **
    @GetMapping("aka/{apodo}")
    public List<ArtistOutputDto> getArtistByApodo(@PathVariable String apodo){
        return artistService.findArtistByApodo(apodo);
    }

    // ** 3 **
    @GetMapping("id/{id}")
    public ArtistOutputDto getArtistById(@PathVariable int id){
        return artistService.findArtistById(id);
    }

    /* ****** */

    /** MÉTODO PUT **/
    @PutMapping("id/{id}")
    public ArtistOutputDto updateArtist(@PathVariable int id,@RequestBody ArtistInputDto artistInputDto){
        return artistService.updateArtist(id, artistInputDto);
    }

    /** MÉTODO DELETE **/
    @DeleteMapping("id/{id}")
    public String deletePerson(@PathVariable int id){
        return artistService.deleteArtist(id);
    }




}