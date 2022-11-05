import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Artist } from '../class/artist';

@Injectable({
  providedIn: 'root'
})
export class ArtistService
{
  private url: string = 'http://localhost:8082/artist';
  
  // Obtener una lista de artistas
  getAll(): Observable<Artist[]>
  {
    return this.http.get<Artist[]>(this.url);
  }
  // Obtener un artista según el id
  getById(id: number): Observable<Artist>
  {
    return this.http.get<Artist>(`${this.url}/id/${id}`);
  }
  // Crear un artista
  create(artist: Artist): Observable<Artist>
  {
    return this.http.post<Artist>(this.url, artist);
  }
  // Actualizar un artista
  update(artist: Artist): Observable<Artist>
  {
    return this.http.put<Artist>(`${this.url}/id/${artist.id}`, artist);
  }

  // Eliminar a un artista
  delete(id: number): Observable<Artist>
  {
    return this.http.delete<Artist>(`${this.url}/id/${id}`);
  }





constructor(private http: HttpClient) {}
}
