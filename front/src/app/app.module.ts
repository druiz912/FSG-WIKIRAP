import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
/* IMPORT COMPONENTS */
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
/* */
import { PersonListComponent } from './components/api/person/lista/person-list.component';
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

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    PersonListComponent,
    PersonFormComponent,
    ArtistComponent,
    ArtistFormComponent,
    AlbumComponent,
    AlbumFormComponent,
    SongComponent,
    SongFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
