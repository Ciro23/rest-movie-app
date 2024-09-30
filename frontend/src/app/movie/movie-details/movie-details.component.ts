import {Component, OnInit} from '@angular/core';
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {BackButtonComponent} from "../../back-button/back-button.component";
import {MovieService} from "../movie.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {CurrencyPipe, DatePipe, NgFor, NgIf} from "@angular/common";
import {Movie} from "../movie";
import {Genre} from "../../genre/genre";
import {Person} from "../../person/person";
import {compareNullableStrings} from "../../collections";

@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [
    ActionButtonsComponent,
    BackButtonComponent,
    NgIf,
    CurrencyPipe,
    DatePipe,
    NgFor,
    RouterLink
  ],
  templateUrl: './movie-details.component.html',
})
export class MovieDetailsComponent implements OnInit {
  movie!: Movie;
  genres: Genre[] = [];
  people: Person[] = [];

  constructor(
    private movieService: MovieService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    const movieId = +this.route.snapshot.paramMap.get('id')!;
    this.movieService.fetchMovie(movieId).subscribe({
      next: (response) => {
        this.movie = response
      },
      error: (_) => {
        void this.router.navigate(['/404'], {skipLocationChange: true})
      },
    });

    this.movieService.fetchGenresByMovieId(movieId)
      .subscribe(genres => this.genres = genres);

    this.movieService.fetchPeopleByMovieId(movieId)
      .subscribe(people => this.people = people);
  }

  getOnEdit(movieId: number): () => void {
    return () => void this.router.navigate([`/movies/${movieId}/edit`]);
  }

  getOnDownloadPdf(movieId: number): () => void {
    return () => this.movieService.downloadPdfFile(movieId);
  }

  getOnDelete(movieId: number): () => void {
    return () => this.movieService.deleteMovie(movieId)
      .subscribe(response => {
        if (response.status === 204) {
          window.history.back();
        }
      });
  }

  formatRuntime(runtime: number): string {
    const hours = Math.floor(runtime / 60);
    const minutes = runtime % 60;
    return `${hours}h ${minutes}m`;
  }

  get directors() {
    return this.people
      .filter(person => this.movie.directorIds.includes(person.id))
      .sort((a, b) => compareNullableStrings(a.lastName, b.lastName));
  }

  get actors() {
    return this.people
      .filter(person => this.movie.actors.map(a => a.actorId).includes(person.id))
      .sort((a, b) => compareNullableStrings(a.lastName, b.lastName));
  }
}
