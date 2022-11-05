import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Person } from '../class/person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.css']
})
export class PersonFormComponent implements OnInit
{
  
  // Instanciamos Person y la guardamos en una variable
  // Para usarla posteriormente en el [(ngModel)]="person.name"
  person: Person = new Person();
  title: string = "Registro de personas"

  // Inyectamos a través del constructor: PersonService y Router
  constructor(
  private service: PersonService,
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
            resp => this.person=resp
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
    console.log('Se ha enviado: ' + this.person);
    this.service.create(this.person).subscribe(
      // Nos suscribimos para recibir de vuelta lo creado
      res => this.router.navigate(['/person'])
    )
  }

  // Método para actualizar una persona
  update(): void
  {
    this.service.update(this.person).subscribe(
      // Una vez que lo actualice nos redirija
      x => this.router.navigate(['/person'])
    )
  }

}
