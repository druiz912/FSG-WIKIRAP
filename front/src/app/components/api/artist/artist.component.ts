import { Component, OnInit } from '@angular/core';
import { Artist } from './class/artist';
import { ArtistService } from './service/artist.service';

@Component({
  selector: 'app-artist',
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css']
})
export class ArtistComponent implements OnInit
{
  title: string = "Listado de artistas"
  artists: Artist[];

  constructor(private service: ArtistService) { }

  ngOnInit(): void
  {
    this.service.getAll()
      .subscribe(
      // funcion flecha ; mapear la respuesta a nuestra variable persons, quedamos suscritos 
      a => this.artists = a
    )
  }

    // Método eliminar
    delete(artist:Artist):void
    {
      this.service.delete(artist.id).subscribe(
        // Función anómina que se subscribe al método getAll
        res => this.service.getAll().subscribe(
          // Función anónima => lo la respuesta de getAll será la variable allArtist que tiene el valor de persons(un array con todas las personas) 
          allArtist => this.artists = allArtist
        )
      )
    }


}
