package com.druiz.fullstack.wikirap.song.application;

import com.druiz.fullstack.wikirap.song.application.port.SongService;
import com.druiz.fullstack.wikirap.song.domain.Song;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongInputDto;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongOutputDto;
import com.druiz.fullstack.wikirap.song.infrastructure.repo.SongRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import com.druiz.fullstack.wikirap.utils.mapper.SongMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepo songRepo;
    private final SongMapper mapper;

    public SongServiceImpl(SongRepo songRepo, SongMapper mapper) {
        this.songRepo = songRepo;
        this.mapper = mapper;
    }

    @Override
    public SongOutputDto addSong(SongInputDto songInputDto) {
        Song song = mapper.mapInputToEntity(songInputDto);
        songRepo.save(song);
        return mapper.mapEntityToOutput(song);
    }

    @Override
    public List<SongOutputDto> addListSongs(List<SongInputDto> songInputDtoList) {
        List<Song> list = new ArrayList<>();
        /* PIPELINE */
        songInputDtoList.forEach( s -> {
            // Mapeamos y convertimos cada elemento de la lista (DTO-SONG) en Entity
            Song song = mapper.mapInputToEntity(s);
            list.add(song);
        });
        // Save
        songRepo.saveAll(list);
        // Convertir a OutputDto
        return mapper.mapListEntityToOutput(list);
    }

    @Override
    public List<SongOutputDto> findAllSongs() {
        List<Song> list = songRepo.findAll();
        return mapper.mapListEntityToOutput(list);
    }

    @Override
    public List<SongOutputDto> findSongByTitle(String title) {
        List<Song> list = songRepo.findSongByTitle(title);
        return mapper.mapListEntityToOutput(list);
    }

    @Override
    public SongOutputDto updateSong(int id, SongInputDto songInputDto) {
        Song songInDB = songRepo.findById(id).orElseThrow(
                ()-> new NotFoundException("Song with id: " + id + " not found"));
        /* Haciendo uso de una función creada para actualizar  */
        songInDB.update(songInputDto);
        songRepo.save(songInDB);
        return mapper.mapEntityToOutput(songInDB);
    }

    @Override
    public String deleteSong(int id) {
        if (songRepo.findById(id).isPresent()){
            songRepo.deleteById(id);
            return "Song with id: " + id + " is deleted";
        } else {
            throw new NotFoundException("Song with id: " + id + " not found");
        }
    }

    @Override
    public SongOutputDto findSongById(int id) {
        Song song = songRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Song with id " + id + " not found"));
        // 2. Mapeamos la entidad a Output
        return mapper.mapEntityToOutput(song);
    }
}
