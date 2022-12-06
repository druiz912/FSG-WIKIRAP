import { Component, OnInit } from '@angular/core';
import { Person } from '../class/person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-person-table',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit
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
      .subscribe( (data:any) => this.persons = data)
    // funcion flecha ; inicializar la variable persons con la data
      
  }

  /* 
  
  

  */

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
