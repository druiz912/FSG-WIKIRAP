import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Artist } from '../class/artist';
import { ArtistService } from '../service/artist.service';

@Component({
  selector: 'app-artist-form',
  templateUrl: './artist-form.component.html',
  styleUrls: ['./artist-form.component.css']
})
export class ArtistFormComponent implements OnInit
{
  // Instanciamos Artist y la guardamos en una variable
  // Para usarla posteriormente en el [(ngModel)]="artist.apodo"
  artist: Artist = new Artist();

  title: string = "Registro de artistas"

  // Inyectamos a través del constructor: PersonService y Router
  constructor(
    private service: ArtistService,
    private router: Router,
    private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit(): void
    {
      // Hay que llamar a la función desde el onInit, ya que así se hace al iniciar el componente
      this.cargar();
    }
  
    cargar(): void
    {
      // Hacemos uso de la variable activatedRoute
      this.activatedRoute.params.subscribe(
        // Función anónima => que me almacene los datos de la persona a editar (ya que en la url viene el id)
        x =>
        {
          let id = x['id']
          if (id)
          {
            this.service.getById(id).subscribe(
              resp => this.artist=resp
            );
          } else
          {
            console.log('ID actual:' + id);
          }
        }
      )
    }
  
    // Método que contiene la lógica de crear una persona
    create(): void
    {
      console.log('Se ha enviado: ' + this.artist);
      this.service.create(this.artist).subscribe(
        // Nos suscribimos para recibir de vuelta lo creado
        res => this.router.navigate(['/artist'])
      )
    }
  
    // Método para actualizar una persona
    update(): void
    {
      this.service.update(this.artist).subscribe(
        // Una vez que lo actualice nos redirija
        x => this.router.navigate(['/artist'])
      )
    }
}
