import {Component, OnInit} from '@angular/core';
import {BackButtonComponent} from "../../back-button/back-button.component";
import {FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {Actor, Movie} from "../movie";
import {MovieService} from "../movie.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormUtil} from "../../form-util";
import {MovieForm} from "../movie-form";
import {Genre} from "../../genre/genre";
import {GenreService} from "../../genre/genre.service";
import {NgbDropdown, NgbDropdownMenu, NgbDropdownToggle} from "@ng-bootstrap/ng-bootstrap";
import {forkJoin, Observable, of} from "rxjs";
import {AutocompleteFieldComponent} from "../../autocomplete-field/autocomplete-field.component";
import {Person} from "../../person/person";
import {SearchablePerson} from "../../person/searchable-person";
import {PersonService} from "../../person/person.service";
import {SaveAndCancelButtonsComponent} from "../../form/save-and-cancel-buttons/save-and-cancel-buttons.component";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-movie-form',
  standalone: true,
  imports: [
    BackButtonComponent,
    FormsModule,
    NgForOf,
    NgIf,
    ReactiveFormsModule,
    NgbDropdown,
    NgbDropdownMenu,
    NgbDropdownToggle,
    AutocompleteFieldComponent,
    NgClass,
    SaveAndCancelButtonsComponent
  ],
  templateUrl: './movie-form.component.html',
})
export class MovieFormComponent implements OnInit {

  /**
   * If undefined, the form is used to add a new user, otherwise
   * the existing one is fetched from the backend to be edited.
   */
  readonly movieId?: number;

  movieForm: MovieForm = {}

  errorMessages: string[] = [];

  allGenres: Genre[] = [];
  selectedGenreIds: number[] = [];

  people: Person[] = [];

  constructor(
    private movieService: MovieService,
    private personService: PersonService,
    private genreService: GenreService,
    private route: ActivatedRoute,
  ) {
    const id = this.route.snapshot.paramMap.get("id");
    this.movieId = id ? +id : undefined;
  }

  ngOnInit(): void {
    if (this.movieId) {
      forkJoin({
        movie: this.movieService.fetchMovie(this.movieId),
        genres: this.genreService.fetchGenres(),
        people: this.movieService.fetchPeopleByMovieId(this.movieId),
      }).subscribe({
        next: ({ movie, genres, people }) => {
          this.allGenres = genres.data;
          this.people = people;
          this.movieForm = this.domainToForm(movie);

          this.selectedGenreIds = this.allGenres
            .filter(genre => movie.genreIds.includes(genre.id))
            .map(genre => genre.id);
        },
        error: (err) => {
          console.error('An error occurred:', err);
        }
      });
    } else {
      this.genreService.fetchGenres().subscribe(genres => {
        this.allGenres = genres.data;
      });
    }
  }

  save(form: NgForm) {
    this.errorMessages = FormUtil.getFormValidationErrors(form);

    if (!this.movieForm.directors || this.movieForm.directors.length === 0) {
      this.errorMessages.push("\"Directors\" is required");
    }

    if (!this.movieForm.actors || this.movieForm.actors.length === 0) {
      this.errorMessages.push("\"Actors\" is required");
    }

    if (!this.movieForm.genres || this.movieForm.genres.length === 0) {
      this.errorMessages.push("\"Genres\" is required");
    }

    if (!form.valid || this.errorMessages.length > 0) {
      return;
    }

    const movie = this.formToDomain(this.movieForm);
    let callable;
    if (this.movieId) {
      callable = this.movieService.updateMovie(movie);
    } else {
      callable = this.movieService.insertMovie(movie);
    }

    callable.subscribe({
      next: (response) => {
        if (response.status == 200 && response.body) {
          window.history.back();
        }
      }, error: (error: HttpErrorResponse) => {
        const errorResponse = error.error;
        this.errorMessages.push(errorResponse.title);
      }
    });
  }

  get releaseDate() {
    if (!this.movieForm.releaseDate) {
      return "";
    }
    return this.movieForm.releaseDate.toISOString().split('T')[0];
  }

  set releaseDate(value: string) {
    if (!value || isNaN(Date.parse(value))) {
      this.movieForm.releaseDate = undefined;
      return;
    }

    this.movieForm.releaseDate = new Date(value);
  }

  getPersonFullName = (person: Person) => {
    return person.name + " " + (person.lastName ?? "");
  }

  onSelectDirectors(people: Person[]) {
    this.movieForm.directors = people;
  }

  onSelectActors(people: Person[]) {
    this.movieForm.actors = people;
  }

  searchPeopleByFullName = (name: string) => {
    const searchablePerson: SearchablePerson = {
      name: name,
    }
    return this.personService.fetchPeople(searchablePerson);
  }

  getGenreName = (genre: Genre) => {
    return genre.name;
  }

  searchGenreByName = (name: string): Observable<Genre[]> => {
    const genres = this.allGenres
      .filter(genre => {
        return genre.name.toLowerCase().includes(name.toLowerCase())
      });
    return of(genres);
  }

  onSelectedGenresChange(genres: Genre[]): void {
    this.movieForm.genres = genres;
  }

  private domainToForm(movie: Movie): MovieForm {
    const movieGenres = this.allGenres
      .filter(genre => movie.genreIds.includes(genre.id));

    const movieDirectors = this.people
      .filter(director => movie.directorIds.includes(director.id));

    const movieActors = this.people
      .filter(actor => movie.actors.map(a => a.actorId).includes(actor.id));

    return {
      id: movie.id,
      title: movie.title,
      releaseDate: new Date(movie.releaseDate),
      runtime: movie.runtime,
      budget: movie.budget,
      boxOffice: movie.boxOffice,
      overview: movie.overview,
      genres: movieGenres,
      directors: movieDirectors,
      actors: movieActors,
    }
  }

  private formToDomain(movieForm: MovieForm): Movie {
    const directors: number[] = [];
    const actors: Actor[] = [];
    let i = 0;

    movieForm.directors?.forEach(director => {
      directors.push(director.id);
      i++;
    })

    movieForm.actors?.forEach(actor => {
      actors.push({
        actorId: actor.id,
        roleName: "",
        castOrder: i,
      })
      i++;
    })

    return {
      id: movieForm.id!,
      title: movieForm.title!,
      releaseDate: movieForm.releaseDate!,
      runtime: movieForm.runtime!,
      budget: movieForm.budget!,
      boxOffice: movieForm.boxOffice,
      overview: movieForm.overview!,
      genreIds: movieForm.genres!.map(genre => genre.id),
      directorIds: directors,
      actors: actors,
    }
  }
}
