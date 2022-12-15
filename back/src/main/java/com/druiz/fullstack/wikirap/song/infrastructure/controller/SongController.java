package com.druiz.fullstack.wikirap.song.infrastructure.controller;

import com.druiz.fullstack.wikirap.song.application.port.SongService;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongInputDto;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongOutputDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("song")
@CrossOrigin(origins = "http://localhost:4200")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    /** ----------- ** POST ** --------------- **/

    // Añadir una persona
    @PostMapping
    public SongOutputDto createASong(@RequestBody SongInputDto songInputDto) {
        return songService.addSong(songInputDto);
    }
    // Añadir una lista de personas
    @PostMapping("add/list")
    public List<SongOutputDto> addListSongs(@RequestBody List<SongInputDto> songInputDtoList){
        return songService.addListSongs(songInputDtoList);
    }

    /** ------------ ** GET ** --------------- **/

    // Obtener una lista con todas las personas
    @GetMapping
    public List<SongOutputDto> getAllSongs() {
        return songService.findAllSongs();
    }
    // Obtener una lista de todas las personas según el nombre
    @GetMapping("/title/{title}")
    public List<SongOutputDto> getAllSongByTitle(@PathVariable String title) {
        return songService.findSongByTitle(title);
    }
    // Obtener una persona según el ID
    @GetMapping("id/{id}")
    public SongOutputDto getSongById (@PathVariable int id){
        return songService.findSongById(id);
    }

    /** ------------ ** PUT & DELETE ** --------------- **/

    @PutMapping("id/{id}")
    public SongOutputDto updateSong(@PathVariable int id, @RequestBody SongInputDto songInputDto){
        return songService.updateSong(id, songInputDto);
    }

    @DeleteMapping("id/{id}")
    public String deleteSong(@PathVariable int id){
        return songService.deleteSong(id);
    }
}
