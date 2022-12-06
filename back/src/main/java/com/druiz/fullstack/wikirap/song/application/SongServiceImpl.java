package com.druiz.fullstack.wikirap.song.application;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.album.infrastructure.repo.AlbumRepo;
import com.druiz.fullstack.wikirap.song.application.port.SongService;
import com.druiz.fullstack.wikirap.song.domain.Song;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongInputDto;
import com.druiz.fullstack.wikirap.song.infrastructure.controller.dto.SongOutputDto;
import com.druiz.fullstack.wikirap.song.infrastructure.repo.SongRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class SongServiceImpl implements SongService {

    private final SongRepo songRepo;
    private final AlbumRepo albumRepo;

    @Override
    public SongOutputDto addSong(SongInputDto songInputDto) {
       Song song = mapToEntity(songInputDto);
       songRepo.save(song);

       return new SongOutputDto(song);
    }

    @Override
    public List<SongOutputDto> addListSongs(List<SongInputDto> songInputDtoList) {
        List<Song> list = new ArrayList<>();
        /* PIPELINE */
        songInputDtoList.forEach( s -> {
            // Mapeamos y convertimos cada elemento de la lista (DTO-SONG) en Entity
            Song song = mapToEntity(s);
            list.add(song);
        });
        // Save
        songRepo.saveAll(list);
        // Convertir a OutputDto
        List<SongOutputDto> outputDtoList = new ArrayList<>();
        list.forEach(entity -> {
            SongOutputDto outputDto = new SongOutputDto(entity);
            outputDtoList.add(outputDto);
        });
        return outputDtoList;
    }

    @Override
    public List<SongOutputDto> findAllSongs() {
        List<Song> list = songRepo.findAll();
        List<SongOutputDto> outputDtoList = new ArrayList<>();
        list.forEach(entity -> {
            SongOutputDto outputDto = new SongOutputDto(entity);
            outputDtoList.add(outputDto);
        });
        return outputDtoList;
    }

    @Override
    public List<SongOutputDto> findSongByTitle(String title) {
        List<Song> list = songRepo.findSongByTitle(title);
        List<SongOutputDto> outputDtoList = new ArrayList<>();
        list.forEach(entity -> {
            SongOutputDto outputDto = new SongOutputDto(entity);
            outputDtoList.add(outputDto);
        });
        return outputDtoList;
    }

    @Override
    public SongOutputDto updateSong(int id, SongInputDto songInputDto) {
        Song songInDB = songRepo.findById(id).orElseThrow(
                ()-> new NotFoundException("Song with id: " + id + " not found"));
        /* Haciendo uso de una función creada para actualizar  */
        songInDB.update(songInputDto);
        songRepo.save(songInDB);
        return new SongOutputDto(songInDB);
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
        return new SongOutputDto(song);
    }

    public Song mapToEntity (SongInputDto dto){
        Song song1 = new Song();
        song1.setAlbum(obtenerAlbumById(dto));
        song1.setTitle(dto.getTitle());
        song1.setDepartureDate(LocalDate.parse(dto.getDepartureDate()));
        song1.setDuration(LocalTime.parse(dto.getDuration()));
        return song1;
    }

    private Album obtenerAlbumById(SongInputDto dto) {
        return albumRepo.findById(dto.getIdAlbum()).orElseThrow(
                () -> new NotFoundException("Album not found"));
    }

}
