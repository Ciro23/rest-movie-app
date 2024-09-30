import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {forkJoin, map, Observable} from "rxjs";
import {Actor, Movie} from "./movie";
import {FileService} from "../file-service";
import {SearchableMovie} from "./searchable-movie";
import {User} from "../user/user";
import {GenreService} from "../genre/genre.service";
import {Genre} from "../genre/genre";
import {Review} from "../review/review";
import {Person} from "../person/person";

@Injectable({providedIn: "root"})
export class MovieService {
  apiUrl = "/api/movies";

  constructor(
    private httpClient: HttpClient,
    private fileService: FileService,
  ) {}

  fetchMovies(searchModel?: SearchableMovie): Observable<Movie[]> {
    const httpParams = this.getHttpParams(searchModel);
    return this.httpClient.get<Movie[]>(this.apiUrl, {params: httpParams});
  }

  fetchMovie(id: number): Observable<Movie> {
    return this.httpClient.get<Movie>(`${this.apiUrl}/${id}`);
  }

  fetchGenresByMovieId(movieId: number) {
    return this.httpClient.get<Genre[]>(`${this.apiUrl}/${movieId}/genres`);
  }

  fetchPeopleByMovieId(movieId: number) {
    return this.httpClient.get<Person[]>(`${this.apiUrl}/${movieId}/people`);
  }

  insertMovie(movie: Movie) {
    return this.httpClient.post<Movie>(this.apiUrl, {
      title: movie.title,
      releaseDate: movie.releaseDate.toISOString().split('T')[0],
      budget: movie.budget,
      boxOffice: movie.boxOffice,
      runtime: movie.runtime,
      genreIds: movie.genreIds,
      directorIds: movie.directorIds,
      actors: movie.actors,
      overview: movie.overview,
    }, { observe: 'response' });
  }

  updateMovie(movie: Movie) {
    return this.httpClient.put<Movie>(`${this.apiUrl}/${movie.id}`, {
      title: movie.title,
      releaseDate: movie.releaseDate.toISOString().split('T')[0],
      budget: movie.budget,
      boxOffice: movie.boxOffice,
      runtime: movie.runtime,
      genreIds: movie.genreIds,
      directorIds: movie.directorIds,
      actors: movie.actors,
      overview: movie.overview,
    }, { observe: 'response' });
  }

  deleteMovie(id: number) {
    return this.httpClient.delete(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  /**
   * Downloads the .xlsx file with list of all the movies.
   */
  downloadXlsxFile(searchModel?: SearchableMovie) {
    const httpParams = this.getHttpParams(searchModel);
    this.fileService.downloadXlsxFile(`${this.apiUrl}/xlsx`, "movies.xlsx", httpParams);
  }

  /**
   * Downloads the .pdf file with the details of a movie, given its id.
   */
  downloadPdfFile(movieId: number) {
    this.fileService.downloadPdfFile(`${this.apiUrl}/${movieId}/pdf`, `movie-${movieId}.pdf`);
  }

  private getHttpParams(searchModel?: SearchableMovie) {
    let params = new HttpParams();
    if (searchModel?.title) {
      params = params.append("title", searchModel.title);
    }

    if (searchModel?.genres) {
      searchModel.genres.forEach((genre: Genre) => {
        params = params.append("genreId", genre.id);
      });
    }

    if (searchModel?.releaseDateStart) {
      params = params.append("releaseDateStart", new Date(searchModel.releaseDateStart).toISOString());
    }

    if (searchModel?.releaseDateEnd) {
      params = params.append("releaseDateEnd", new Date(searchModel.releaseDateEnd).toISOString());
    }

    if (searchModel?.budgetStart) {
      params = params.append("budgetStart", searchModel.budgetStart);
    }

    if (searchModel?.budgetEnd) {
      params = params.append("budgetEnd", searchModel.budgetEnd);
    }

    if (searchModel?.boxOfficeStart) {
      params = params.append("boxOfficeStart", searchModel.boxOfficeStart);
    }

    if (searchModel?.boxOfficeEnd) {
      params = params.append("boxOfficeEnd", searchModel.boxOfficeEnd);
    }

    if (searchModel?.runtimeStart) {
      params = params.append("runtimeStart", searchModel.runtimeStart);
    }

    if (searchModel?.runtimeEnd) {
      params = params.append("runtimeEnd", searchModel.runtimeEnd);
    }

    return params;
  }
}
