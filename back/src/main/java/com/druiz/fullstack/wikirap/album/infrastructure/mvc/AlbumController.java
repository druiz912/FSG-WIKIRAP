package com.druiz.fullstack.wikirap.album.infrastructure.mvc;

import com.druiz.fullstack.wikirap.album.application.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;
/*
    @RequestMapping(value = "/addDiscos")
    public ModelAndView addDiscos(
            @RequestParam(required = false) String portada,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String duracion,
            @RequestParam(required = false) String fechaSalida){
        if (titulo != null) {
            Albums album = new Albums(portada, titulo, autor, duracion, fechaSalida);
            albumService.create(disc);
        }
        //Creamos el ModelAndView
        ModelAndView mav = new ModelAndView(); //Ahora ModelAndView es mav
        //Obtenemos la lista de peliculas
        List<Discos> disc = albumService.findAll();
        //Le damos nombre al ModelAndView
        mav.setViewName("crear_discos.html");
        mav.addObject("Discos", disc);
        return mav;
    }
*/
}
