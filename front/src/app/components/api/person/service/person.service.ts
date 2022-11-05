import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Person } from '../class/person';

@Injectable({
  providedIn: 'root',
})
export class PersonService {
  private url: string = 'http://localhost:8082/person';

  constructor(private http: HttpClient) {}
  // Obtener lista de personas => http://localhost:8082/person
  getAll(): Observable<Person[]> {
    return this.http.get<Person[]>(this.url);
  }

  // Crear persona
  create(person: Person): Observable<Person> {
    return this.http.post<Person>(this.url, person);
  }

  // Obtener una persona según id
  getById(id: number): Observable<Person> {
    return this.http.get<Person>(`${this.url}/id/${id}`);
  }

  // Actualizar a una persona
  update(person: Person): Observable<Person> {
    return this.http.put<Person>(`${this.url}/id/${person.idPerson}`, person);
  }

  // Eliminar a una persona
  delete(id: number): Observable<Person> {
    return this.http.delete<Person>(`${this.url}/id/${id}`);
  }

  // Buscar persona por nombre
  findByName(person: Person): Observable<Person> {
    return this.http.get<Person>(`${this.url}/name/${person.name}`);
  }
}
