import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {UserTableComponent} from "../../user/user-table/user-table.component";
import {MovieTableComponent} from "../movie-table/movie-table.component";
import {MovieService} from "../movie.service";
import {Movie} from "../movie";
import {SearchableMovie} from "../searchable-movie";
import {NgIf} from "@angular/common";
import {MultiSelectDropdownComponent} from "../../multi-select-dropdown/multi-select-dropdown.component";
import {Genre} from "../../genre/genre";
import {GenreService} from "../../genre/genre.service";
import {AutocompleteFieldComponent} from "../../autocomplete-field/autocomplete-field.component";
import {Observable, of} from "rxjs";
import {SearchAndResetButtonsComponent} from "../../form/search-and-reset-buttons/search-and-reset-buttons.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmationModalComponent} from "../../confirmation-modal/confirmation-modal.component";

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [
    FormsModule,
    UserTableComponent,
    MovieTableComponent,
    NgIf,
    MultiSelectDropdownComponent,
    AutocompleteFieldComponent,
    SearchAndResetButtonsComponent
  ],
  templateUrl: './movie-list.component.html',
})
export class MovieListComponent implements OnInit {
  movies: Movie[] = [];
  searchModel: SearchableMovie = {};

  genres: Genre[] = [];

  constructor(
    private movieService: MovieService,
    private genreService: GenreService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.search();
  }

  search() {
    this.movieService.fetchMovies(this.searchModel).subscribe(movies => {
      this.movies = movies;
    })
    this.genreService.fetchGenres().subscribe(genres => {
      this.genres = genres;
    })
  }

  get filterOn(): boolean {
    return (
      this.searchModel.title !== undefined && this.searchModel.title !== "" ||
      this.searchModel.releaseDateStart !== undefined ||
      this.searchModel.releaseDateEnd !== undefined ||
      this.searchModel.budgetStart !== undefined ||
      this.searchModel.budgetEnd !== undefined ||
      this.searchModel.boxOfficeStart !== undefined ||
      this.searchModel.boxOfficeEnd !== undefined ||
      this.searchModel.runtimeStart !== undefined ||
      this.searchModel.runtimeEnd !== undefined ||
      this.searchModel.genres !== undefined && this.searchModel.genres.length > 0
    );
  }

  getGenreName = (genre: Genre) => {
    return genre.name;
  }

  searchGenreByName = (name: string): Observable<Genre[]> => {
    const genres = this.genres
      .filter(genre => {
        return genre.name.toLowerCase().includes(name.toLowerCase())
      });
    return of(genres);
  }

  onSelectedGenresChange(genres: Genre[]): void {
    this.searchModel.genres = genres;
  }

  resetFilter = () => {
    this.searchModel = {};
    this.search();
  }
}
