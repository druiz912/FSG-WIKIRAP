package com.druiz.fullstack.wikirap.artist.infrastructure.controller.mvc;

import com.druiz.fullstack.wikirap.artist.application.port.ArtistService;
import com.druiz.fullstack.wikirap.artist.domain.Artist;
import com.druiz.fullstack.wikirap.artist.infrastructure.controller.dto.ArtistOutputDto;
import com.druiz.fullstack.wikirap.utils.mapper.ArtistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArtistController {

    private final ArtistService artistasService;

    public ArtistController(ArtistService artistasService) {
        this.artistasService = artistasService;
    }

    @RequestMapping(value = "/addArtista")
    public ModelAndView addArtista(
            @RequestParam(required = false) String apodo,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) String ocupation,
            @RequestParam(required = false) String periodoActivo,
            @RequestParam(required = false) String details) {
        if (apodo != null) {
            //Artist art = new Artist(apodo, imageUrl, ocupation, periodoActivo, details);
            //ArtistMapper.INSTANCE.mapEntityToOutput(art);
        }
        //Creamos el ModelAndView
        ModelAndView mav = new ModelAndView(); //Ahora ModelAndView es mav
        //Obtenemos la lista de peliculas
        //List<Artist> artist = artistasService.findAllArtists().stream().map(ArtistOutputDto::new);
        //Le damos nombre al ModelAndView
        mav.setViewName("crear_artista.html");
        //mav.addObject("Artistas", artist);
        return mav;
    }

}