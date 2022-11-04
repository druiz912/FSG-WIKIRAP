package com.druiz.fullstack.wikirap.album.infrastructure.rest;

import com.druiz.fullstack.wikirap.album.application.AlbumService;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/** CONTROLADOR **/

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("album")
public class AlbumController {

    private final AlbumService albumService;

    /** INYECCIÓN POR CONSTRUCTOR **/
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /* ENDPOINT PARA AÑADIR UN ÁLBUM  */
    @PostMapping
    public AlbumOutputDto createAlbum(@RequestBody @Valid AlbumInputDto albumInputDto) {
        return albumService.addAlbum(albumInputDto);
    }

    /* ENDPOINT PARA ACTUALIZAR UN ÁLBUM */
    @PutMapping("id/{id}")
    public AlbumOutputDto updateAlbum(@PathVariable int id, @RequestBody @Valid AlbumInputDto albumInputDto){
        return albumService.updateAlbum(id, albumInputDto);
    }

    /* ENDPOINT PARA ELIMINAR UN ÁLBUM */
    @DeleteMapping("id/{id}")
    public void deleteAlbum(@PathVariable int id) {
        albumService.deleteAlbum(id);
    }

    /* --------------------------- */

    /* ENDPOINT PARA OBTENER UNA LISTA DE TODOS LOS ÁLBUMES */
    @GetMapping
    List<AlbumOutputDto> getAllAlbums() {
        return albumService.findAllAlbum();
    }

    /* ENDPOINT PARA OBTENER UNA LISTA DE ÁLBUMES SEGÚN EL TÍTULO */
    @GetMapping("title/{title}")
    List<AlbumOutputDto> getAlbumsByTitle(@PathVariable String title) {
        return albumService.findAlbumByTitle(title);
    }

    /* ENDPOINT PARA OBTENER ÁLBUM SEGÚN SU IDENTIFICADOR */
    @GetMapping("id/{id}")
    public AlbumOutputDto getDiscosById(@PathVariable int id) {
        return albumService.findAlbumById(id);
    }




}