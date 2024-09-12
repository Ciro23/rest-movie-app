import {Component, Input} from '@angular/core';
import {TableComponent} from "../../table/table.component";
import {Movie} from "../movie";
import {Router} from "@angular/router";
import {MovieService} from "../movie.service";
import {SearchableMovie} from "../searchable-movie";
import {TableField} from "../../table/table-field";

@Component({
  selector: 'app-movie-table',
  standalone: true,
  imports: [
    TableComponent
  ],
  templateUrl: './movie-table.component.html',
})
export class MovieTableComponent {
  @Input() movies: Movie[] = [];
  @Input() searchModel?: SearchableMovie;

  fields: TableField[] = [
    { name: "Title", getRawValue: (movie: Movie) => movie.title },
    { name: "Release Date", getRawValue: (movie: Movie) => movie.releaseDate },
    {
      name: "Box Office",
      getRawValue: (movie: Movie) => movie.boxOffice,
      getFormattedValue: (movie: Movie) => this.formatCurrency(movie.boxOffice)
    },
    { name: "Runtime", getRawValue: (movie: Movie) => movie.runtime }
  ];

  constructor(private movieService: MovieService, private router: Router) {}

  view = (userId: number) => {
    void this.router.navigate([`/movies/${userId}`]);
  }

  createNew = () => {
    void this.router.navigate(["/movies/new"]);
  }

  edit = (userId: number) => {
    void this.router.navigate([`/movies/${userId}/edit`]);
  }

  downloadPdf = (userId: number) => {
    this.movieService.downloadPdfFile(userId);
  }

  downloadXlsx = () => {
    this.movieService.downloadXlsxFile(this.searchModel);
  }

  delete = (userId: number) => {
    this.movieService.deleteMovie(userId)
      .subscribe(response => {
        if (response.status === 204) {
          this.movies = this.movies.filter(user => user.id !== userId);
        }
      });
  }

  formatCurrency(value?: number): string {
    if (!value) {
      return "N/A";
    }

    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 0,
    }).format(value);
  }
}
