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
            Person personFind = findId(artistInputDto.getIdPerson());
            /* validación para saber si ya hay un artista registrado con ese ID */
            if (personFind == null || artistRepo.getPersonQuery(artistInputDto.getIdPerson()) != null)
                throw new UnprocessableException("Artist object must have a correct person reference.");
            /* Instanciamos objeto Artist para pasarle el inputDto y la persona encontrada */
            Artist artist = new Artist(artistInputDto, personFind);
            /* Una vez convertido a Entidad, lo guardamos */
            artistRepo.save(artist);
            return new ArtistOutputDto(artist);
        } catch (UnprocessableException e) {
            e.getStackTrace();
        }
        throw new UnprocessableException("No se ha podido crear el artista");
    }

    /** MÉTODO PARA AÑADIR UNA LISTA DE ARTISTAS **/
    @Override
    public List<ArtistOutputDto> addListArtists(List<ArtistInputDto> artistInputDtoList) {
        List<Artist> artistList = mapListInputDtoToEntity(artistInputDtoList);
        // Convertir a OutputDto
        return mapListEntityToDto(artistList);
    }

    /** MÉTODO PARA ACTUALIZAR UN ARTISTA **/
    @Override
    public ArtistOutputDto updateArtist(int id, ArtistInputDto artistInputDto) {
        /* Uso de función para encontrar ID de la persona */
        Person personInDB = findId(artistInputDto.getIdPerson());
        /* */
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
        return mapListEntityToDto(artistList);
    }
    // ** 3 **
    @Override
    public List<ArtistOutputDto> findAllArtists() {
        List<Artist> artistList = artistRepo.findAll();
        return mapListEntityToDto(artistList);
    }

    /**  FUNCIONES AUXILIARES  **/

    /** MÉTODO PARA ENCONTRAR UNA PERSONA
     * @param idPerson
     * @return Person
     */
    protected Person findId(Integer idPerson){
        return personRepo.findById(idPerson).orElseThrow(
                ()-> new NotFoundException("This id for Person: " + idPerson + " has not been found"));
    }

    protected List<ArtistOutputDto> mapListEntityToDto(List<Artist> artistList){
        List<ArtistOutputDto> result = new ArrayList<>();
        /* Bucle para iterar la lista ya guardada en la BB DD y mapearla a outputDto */
        artistList.forEach( ar -> {
            /* Instanciamos dto para pasarle cada elemento de la lista */
            ArtistOutputDto outputDto = new ArtistOutputDto(ar);
            /* Añadimos cada elemento ya mapeado a outputDto a la lista result */
            result.add(outputDto);
        } );
        return result;
    }

    protected List<Artist> mapListInputDtoToEntity(List<ArtistInputDto> listDto){
        /* Instanciamos una lista de tipo Artist */
        List<Artist> artistList = new ArrayList<>();
        /* Bucle para iterar y mapear cada elemento de la lista ≥ pasada por parámetros */
        listDto.forEach( a -> {
            /* Comprobación de la existencia del ID de Persona */
            int idPerson = a.getIdPerson();
            Person person = findId(idPerson);
            /* Inicializamos una variable del tipo Artist y le pasamos cada elemento de la lista */
            Artist artist = new Artist(a, person);
            /* Agregamos al ArrayList de Artistas */
            artistList.add(artist);
        });
        // Save
        artistRepo.saveAll(artistList);
        return artistList;
    }

}
