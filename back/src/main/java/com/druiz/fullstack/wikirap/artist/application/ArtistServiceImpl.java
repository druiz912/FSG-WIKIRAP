package com.druiz.fullstack.wikirap.artist.application;

import com.druiz.fullstack.wikirap.artist.application.port.ArtistService;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.repo.ArtistRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import com.druiz.fullstack.wikirap.utils.mapper.ArtistMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepo artistRepo;
    private final ArtistMapper mapper;

    /** INYECCIÓN POR CONSTRUCTOR **/
    public ArtistServiceImpl(ArtistRepo artistRepo, ArtistMapper mapper) {
        this.artistRepo = artistRepo;
        this.mapper = mapper;
    }

    /** MÉTODO PARA AÑADIR UN ARTISTA **/
    @Override
    public ArtistOutputDto addArtist(ArtistInputDto artistInputDto) {
        Artist artist = artistRepo.save(ArtistMapper.INSTANCE.mapInputToEntity(artistInputDto));
        return ArtistMapper.INSTANCE.mapEntityToOutput(artist);
    }

    /** MÉTODO PARA AÑADIR UNA LISTA DE ARTISTAS **/
    @Override
    public List<ArtistOutputDto> addListArtists(List<ArtistInputDto> artistInputDtoList) {
        List<Artist> artistList = new ArrayList<>();
        artistInputDtoList.forEach( a -> {
            var artist = mapper.mapInputToEntity(a);
            artistList.add(artist);
        });
        // Save
        artistRepo.saveAll(artistList);
        // Convertir a OutputDto
        return mapper.mapListEntityToOutput(artistList);
    }

    /** MÉTODO PARA ACTUALIZAR UN ARTISTA **/
    @Override
    public ArtistOutputDto updateArtist(int id, ArtistInputDto artistInputDto) {
        Artist artist = mapper.mapInputToEntity(artistInputDto);
        if(artistRepo.findById(id).isPresent()){
            artist.update(artistInputDto);
            artistRepo.save(artist);
            return ArtistMapper.INSTANCE.mapEntityToOutput(artist);
        } else {
            throw new NotFoundException("Artist with id " + id + " not found");
        }
    }

    /** MÉTODO PARA ELIMINAR UN ARTISTA **/
    @Override
    public String deleteArtist(int id) {
        if(artistRepo.findById(id).isPresent()){
            artistRepo.deleteById(id);
            return "Artist with id " + id + " is deleted";
        } else {
            throw new NotFoundException("Artist with id " + id + " not found");
        }

    }

    /** MÉTODOS DE BÚSQUEDA:
     *  1. Buscar artista por ID
     *  2. Buscar artistas por Apodo
     *  3. Buscar todos los artistas
     * **/

    // ** 1 **
    @Override
    public ArtistOutputDto findArtistById(int id) {
        Artist artist = artistRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Artist with id " + id + " not found"));
        return ArtistMapper.INSTANCE.mapEntityToOutput(artist);
    }

    // ** 2 **
    @Override
    public List<ArtistOutputDto> findArtistByApodo(String apodo) {
        List<Artist> artistList = artistRepo.findByApodo(apodo);
        return ArtistMapper.INSTANCE.mapListEntityToOutput(artistList);
    }
    // ** 3 **
    @Override
    public List<ArtistOutputDto> findAllArtists() {
        List<Artist> artistList = artistRepo.findAll();
        return ArtistMapper.INSTANCE.mapListEntityToOutput(artistList);
    }
}
