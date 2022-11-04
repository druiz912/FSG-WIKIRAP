package com.druiz.fullstack.wikirap.album.application;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.repo.AlbumRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import com.druiz.fullstack.wikirap.utils.mapper.AlbumMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepo albumRepo;
    private final AlbumMapper mapper;
    public AlbumServiceImpl(AlbumRepo albumRepo, AlbumMapper mapper) {
        this.albumRepo = albumRepo;
        this.mapper = mapper;
    }

    /** MÉTODO PARA AÑADIR UN ÁLBUM RECIBIENDO POR PARÁMETROS UN DTO **/
    @Override
    public AlbumOutputDto addAlbum(AlbumInputDto albumInputDto) {
        Album albumMap = mapper.mapInputToEntity(albumInputDto);
        Album album = albumRepo.save(albumMap);
        return mapper.mapEntityToOutput(album);
    }

    /** MÉTODO PARA ACTUALIZAR UNA PERSONA RECIBIENDO POR PARÁMETROS UN DTO & UN ID **/
    @Override
    public AlbumOutputDto updateAlbum(int id, AlbumInputDto albumInputDto) {
        // 1. Comprobar si existe tal id
        Album albumInDB = albumRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Album with id " + id + " not found"));
        // 2. Usamos método update para actualizar entidad
        albumInDB.update(albumInputDto);
        albumRepo.save(albumInDB);
        // 3. Return usando el mapper
        return mapper.mapEntityToOutput(albumInDB);
    }

    /** MÉTODO PARA ELIMINAR UN ALBUM **/
    @Override
    public void deleteAlbum(int id) {
        if (albumRepo.findById(id).isPresent()) {
            albumRepo.deleteById(id);
        } else {
            throw new NotFoundException("Album with id " + id + " not found");
        }

    }

    /** MÉTODO PARA AÑADIR UNA LISTA DE ÁLBUMES **/
    @Override
    public List<AlbumOutputDto> addListAlbums(List<AlbumInputDto> albumInputDtoList) {
        List<Album> albumList = new ArrayList<>();
        albumInputDtoList.forEach( p-> {
            var album = mapper.mapInputToEntity(p);
            albumList.add(album);
        });
        // Save
        albumRepo.saveAll(albumList);
        // Convertir a OutputDto
        return mapper.mapListEntityToOutput(albumList);
    }

    /** MÉTODOS DE BÚSQUEDA:
     *  1. Buscar álbum por ID
     *  2. Buscar álbum por Title
     *  3. Buscar todos los álbumes
     * **/

    // *-* 1 *-* //
    @Override
    public AlbumOutputDto findAlbumById(int id) {
        // 1. Buscamos tal id
        Album album = albumRepo.findById(id).orElseThrow(
                ()-> new NotFoundException("Album with id " + id + " not found"));
        // 2. Mapeamos la entidad a Output
        return mapper.mapEntityToOutput(album);
    }
    // *-* 2 *-* //
    @Override
    public List<AlbumOutputDto> findAlbumByTitle(String title) {
        List<Album> albums = albumRepo.findByTitle(title);
        return mapper.mapListEntityToOutput(albums);
    }
    // *-* 3 *-* //
    @Override
    public List<AlbumOutputDto> findAllAlbum() {
        List<Album> albums = albumRepo.findAll();
        return mapper.mapListEntityToOutput(albums);
    }
}
