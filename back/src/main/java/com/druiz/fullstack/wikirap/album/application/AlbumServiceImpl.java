package com.druiz.fullstack.wikirap.album.application;

import com.druiz.fullstack.wikirap.album.domain.Album;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumInputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.dto.AlbumOutputDto;
import com.druiz.fullstack.wikirap.album.infrastructure.repo.AlbumRepo;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.repo.ArtistRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepo albumRepo;

    private final ArtistRepo artistRepo;


    /**
     * MÉTODO PARA AÑADIR UN ÁLBUM RECIBIENDO POR PARÁMETROS UN DTO
     **/
    @Override
    public AlbumOutputDto addAlbum(AlbumInputDto albumInputDto) {
        Album album = albumRepo.getArtistQuery(albumInputDto.getIdArtist());
        albumRepo.save(album);
        return new AlbumOutputDto(album);
    }

    /**
     * MÉTODO PARA ACTUALIZAR UNA PERSONA RECIBIENDO POR PARÁMETROS UN DTO & UN ID
     **/
    @Override
    public AlbumOutputDto updateAlbum(int id, AlbumInputDto albumInputDto) {
        if(albumRepo.findById(id).isPresent()){
            var mapeo = mapToEntity(albumInputDto);
            Album album0 = new Album();
            album0.update(mapeo);
            albumRepo.save(mapeo);
            return new AlbumOutputDto(album0);
        } else {
            throw new NotFoundException("Not found album with id: " + id );
        }
    }

    /**
     * MÉTODO PARA ELIMINAR UN ALBUM
     **/
    @Override
    public void deleteAlbum(int id) {
        if (albumRepo.findById(id).isPresent()) {
            albumRepo.deleteById(id);
        } else {
            throw new NotFoundException("Album with id " + id + " not found");
        }

    }

    /**
     * MÉTODO PARA AÑADIR UNA LISTA DE ÁLBUMES
     **/
    @Override
    public List<AlbumOutputDto> addListAlbums(List<AlbumInputDto> albumInputDtoList) {
        List<Album> albumList = new ArrayList<>();
        albumInputDtoList.forEach(albumDto -> {
            Album album = mapToEntity(albumDto);
            albumList.add(album);
        });
        // Save
        albumRepo.saveAll(albumList);
        // Función para mapear lista de Album a lista OutputDto
       return mapListEntityToDto(albumList);
    }

    /**
     * MÉTODOS DE BÚSQUEDA:
     * 1. Buscar álbum por ID
     * 2. Buscar álbum por Title
     * 3. Buscar todos los álbumes
     **/

    // *-* 1 *-* //
    @Override
    public AlbumOutputDto findAlbumById(int id) {
        // 1. Buscamos tal id
        Album album = albumRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Album with id " + id + " not found"));
        // 2. Mapeamos la entidad a Output
        return new AlbumOutputDto(album);
    }

    // *-* 2 *-* //
    @Override
    public List<AlbumOutputDto> findAlbumByTitle(String title) {
        List<Album> albumList = albumRepo.findByTitle(title);
        return mapListEntityToDto(albumList);
    }

    // *-* 3 *-* //
    @Override
    public List<AlbumOutputDto> findAllAlbum() {
        List<Album> albumList = albumRepo.findAll();
        return mapListEntityToDto(albumList);
    }

    // TODO: COMPROBAR LA FUNCIONALIDAD DEL MÉTODO DEL REPO (QUERY SQL)
    @Override
    public List<AlbumOutputDto> findAlbumByArtist(String name) {
        // Usando método del repo para buscar en la BB DD según el nombre del artista
        var albums = albumRepo.findByArtist(name);
        return mapListEntityToDto(albums);
    }


    public Album mapToEntity (AlbumInputDto dto){
        Album album1 = new Album();
        album1.setArtists(obtenerArtistById(dto));
        album1.setTitle(dto.getTitle());
        album1.setTitlePage(dto.getUrlPortada());
        album1.setDuration(dto.getDuration());
        album1.setDepartureDate(dto.getFechaSalida());
        return album1;
    }

    private Artist obtenerArtistById(AlbumInputDto dto) {
        /* var x = artistRepo.findById(dto.getIdArtist()).orElseThrow(
                () -> new NotFoundException("Artist not found"));
        List<Artist> artistList = new ArrayList<>();
        artistList.add(x);
        */

        return artistRepo.findById(dto.getIdArtist()).orElseThrow(
                () -> new NotFoundException("Artist not found"));
    }

    protected List<AlbumOutputDto> mapListEntityToDto(List<Album> albumList){
        List<AlbumOutputDto> result = new ArrayList<>();
        // Bucle forEach para mapear cada entidad Album a Dto
        albumList.forEach(a -> {
            AlbumOutputDto dto = new AlbumOutputDto(a);
            // Añadimos cada dto a la lista
            result.add(dto);
        });
        return result;
    }


}
