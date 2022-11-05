import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
/* IMPORT COMPONENTS */
import { HomeComponent } from './components/home/home.component';
/* */
import { PersonComponent } from './components/api/person/person.component';
import { PersonFormComponent } from './components/api/person/form/person-form.component';
/* */
import { ArtistComponent } from './components/api/artist/artist.component';
import { ArtistFormComponent } from './components/api/artist/form/artist-form.component';
/* */
import { AlbumComponent } from './components/api/album/album.component';
import { AlbumFormComponent } from './components/api/album/form/album-form.component';
/* */
import { SongComponent } from './components/api/song/song.component';
import { SongFormComponent } from './components/api/song/form/song-form.component';


const routes: Routes = [
  // 
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  // Página principal
  { path: 'home', component: HomeComponent, pathMatch: 'full' },

  /* PERSON */
  // Ruta al PersonComponent (Lista)
  { path: 'persons', component: PersonComponent, pathMatch: 'full' },
  // Ruta para el formulario
  { path: 'person/form', component: PersonFormComponent },
  // Ruta para editar persona pasándole un parámetro
  { path: 'person/form/:id', component: PersonFormComponent },

  /* ARTIST */
  // Ruta a ArtistComponent (Lista)
  { path:'artists', component:ArtistComponent },
  // Ruta al formulario de registro de Artistas
  { path: 'artists/form', component: ArtistFormComponent },
  // Ruta al formulario de registro de Artistas pasándole id
  { path: 'artists/form/:id', component: ArtistFormComponent },
  
  /* ALBUM */
  // Ruta a AlbumComponent (Lista)
  { path: 'albums', component: AlbumComponent },
  
  /* SONG */
  // Ruta a SongComponent (Lista)
  { path:'songs', component:SongComponent }
];

@NgModule({
  // Importamos RouterModule y le pasamos a su método forRoot(routes) y lo exportamos
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
