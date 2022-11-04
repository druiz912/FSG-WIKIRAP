package com.druiz.fullstack.wikirap.album.application;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.repo.AlbumRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import com.druiz.fullstack.wikirap.utils.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepo albumRepo;

    @Autowired
    AlbumMapper mapper;

    @Override
    public AlbumOutputDto addAlbum(AlbumInputDto albumInputDto) {
        Album albumMap = mapper.mapInputToEntity(albumInputDto);
        Album album = albumRepo.save(albumMap);
        return mapper.mapEntityToOutput(album);
    }

    @Override
    public AlbumOutputDto updateAlbum(int id, AlbumInputDto albumInputDto) {
        // 1. Comprobar si existe tal id
        Album albumInDB = albumRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Album with id " + id + " not found"));
        // 2. Usamos método update para actualizar entidad
        albumInDB.update(albumInputDto);
        albumRepo.save(albumInDB);
        // 3. Return usando el mapper
        return mapper.mapEntityToOutput(albumInDB);
    }

    @Override
    public void deleteAlbum(int id) {
        if (albumRepo.findById(id).isPresent()) {
            albumRepo.deleteById(id);
        } else {
            throw new NotFoundException("Album with id " + id + " not found");
        }

    }

    @Override
    public List<AlbumOutputDto> addListAlbums(List<AlbumInputDto> albumInputDtoList) {
        // TODO
        return null;
    }

    // FIND

    @Override
    public AlbumOutputDto findAlbumById(int id) {
        // 1. Buscamos tal id
        Album album = albumRepo.findById(id).orElseThrow(()-> new RuntimeException("Album with id " + id + " not found"));
        // 2. Mapeamos la entidad a Output
        return mapper.mapEntityToOutput(album);
    }

    @Override
    public List<AlbumOutputDto> findAlbumByTitle(String title) {
        List<Album> albums = albumRepo.findByTitle(title);
        return mapper.mapListEntityToOutput(albums);
    }


    @Override
    public List<AlbumOutputDto> findAllAlbum() {
        List<Album> albums = albumRepo.findAll();
        return mapper.mapListEntityToOutput(albums);
    }
}
