package com.druiz.fullstack.wikirap.artist.application;

import com.druiz.fullstack.wikirap.artist.application.port.ArtistService;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistInputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import com.druiz.fullstack.wikirap.artist.infrastructure.repo.ArtistRepo;
import com.druiz.fullstack.wikirap.person.domain.Person;
import com.druiz.fullstack.wikirap.person.infrastructure.repo.PersonRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.NotFoundException;
import com.druiz.fullstack.wikirap.utils.exceptions.UnprocessableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepo artistRepo;
    private final PersonRepo personRepo;

    /** MÉTODO PARA AÑADIR UN ARTISTA **/
    @Override
    public ArtistOutputDto addArtist(ArtistInputDto artistInputDto) {
        try {
            /* Instanciamos persona y buscamos el ID en la BB DD para que nos devuelva esa persona */
            Person personFind = personRepo.findById(artistInputDto.getIdPerson()).orElseThrow(
                    () -> new NotFoundException("No se encontró el id en Persona: " + artistInputDto.getIdPerson()));
            /* validación para saber si ya hay un artista registrado con ese ID */
            if (personFind == null || artistRepo.getPersonQuery(artistInputDto.getIdPerson()) != null)
                throw new UnprocessableException("Artist object must have a correct person reference.");
            /* Instanciamos objeto Artist para pasarle el inputDto y la person */
            Artist artist = new Artist(artistInputDto, personFind);
            /* Una vez convertido a Entidad, lo guardamos */
            artistRepo.save(artist);
            return new ArtistOutputDto(artist);
        } catch (UnprocessableException e) {
            e.getStackTrace();
        }
        throw new UnprocessableException("Algo ha ido muy mal");
    }

    /** MÉTODO PARA AÑADIR UNA LISTA DE ARTISTAS **/
    @Override
    public List<ArtistOutputDto> addListArtists(List<ArtistInputDto> artistInputDtoList) {
        /* */
        List<Artist> artistList = new ArrayList<>();
        /* Bucle para iterar y mapear cada elemento de la lista */
        artistInputDtoList.forEach( a -> {
            /* Comprobación de la existencia del ID de Persona */
            Person person = personRepo.findById(a.getIdPerson())
                    /* Mapeamos error por si no existe el ID */
                    .orElseThrow(() -> new NotFoundException(
                            "No se encontró el id de la persona relacionada: " + a.getIdPerson()));
            /* Inicializamos una variable del tipo Artist y le pasamos cada elemento de la lista */
            Artist artist = new Artist(a);
            /* Seteamos la persona pasándole la supuesta persona encontrada por el ID */
            artist.setPerson(person);
            /* Agregamos al ArrayList de Artistas */
            artistList.add(artist);
        });
        // Save
        artistRepo.saveAll(artistList);
        // Convertir a OutputDto
        List<ArtistOutputDto> result = new ArrayList<>();
                artistList.forEach( ar -> {
                    ArtistOutputDto outputDto = new ArtistOutputDto(ar);
                    result.add(outputDto);
                } );
        return result;
    }

    /** MÉTODO PARA ACTUALIZAR UN ARTISTA **/
    @Override
    public ArtistOutputDto updateArtist(int id, ArtistInputDto artistInputDto) {
        Person personInDB = personRepo.findById(artistInputDto.getIdPerson()).orElseThrow(
                ()-> new NotFoundException("This id for Person: " + artistInputDto.getIdPerson() + " has not been found"));
        Artist artistInDB = artistRepo.findById(id).orElseThrow(
                ()-> new NotFoundException("This id for Artist: " + id + " has not been found"));
       if (personInDB.getIdPerson() != 0 && artistInDB.getIdArtist() != 0){
           artistInDB.update(artistInputDto);
           artistRepo.save(artistInDB);
       }
        return new ArtistOutputDto(artistInDB);
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
        return new ArtistOutputDto(artist);
    }


    // ** 2 **
    @Override
    public List<ArtistOutputDto> findArtistByApodo(String apodo) {
        List<Artist> artistList = artistRepo.findByApodo(apodo);
        // Lista output
        List<ArtistOutputDto> result = new ArrayList<>();
        // Mapeo
        artistList.forEach(ar -> {
            ArtistOutputDto artistOutput = new ArtistOutputDto(ar);
            result.add(artistOutput);
        });
        return result;
    }
    // ** 3 **
    @Override
    public List<ArtistOutputDto> findAllArtists() {
        List<Artist> artistList = artistRepo.findAll();
        // Output
        List<ArtistOutputDto> result = new ArrayList<>();
        // Mapeo
        artistList.forEach(ar -> {
            ArtistOutputDto artistOutput = new ArtistOutputDto(ar);
            result.add(artistOutput);
        });
        return result;
    }

    /**  DTO   **/



}
