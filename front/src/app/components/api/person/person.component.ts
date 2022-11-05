import { Component, OnInit } from '@angular/core';
import { Person } from './class/person';
import { PersonService } from './service/person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit
{
  title: string = "Listado de personas"

  // Esta variable irá en el *ngFor = "let person of persons"
  persons: Person[];

  // Inyectamos a través del constructor el servicio 
  constructor(private service:PersonService) { }

  ngOnInit(): void
  {
    // Al iniciar que haga una llamada 
    this.service.getAll()
      .subscribe(
        // funcion flecha ; mapear la respuesta a nuestra variable persons, quedamos suscritos 
        p => this.persons = p
      );
  }

  // Método eliminar
  delete(person:Person):void
  {
    this.service.delete(person.idPerson).subscribe(
      // Función anómina que se subscribe al método getAll
      res => this.service.getAll().subscribe(
        // Función anónima => lo la respuesta de getAll será la variable AllPersons que tiene el valor de persons(un array con todas las personas) 
        allPersons => this.persons = allPersons
      )
    )
  }

}
