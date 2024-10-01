import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {FileService} from "../file-service";
import {Genre} from "./genre";
import {Observable} from "rxjs";
import {PaginatedResponse} from "../paginated-response";

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  apiUrl = "/api/genres";

  constructor(private httpClient: HttpClient, private fileService: FileService) {}

  fetchGenres(searchModel?: Genre): Observable<PaginatedResponse<Genre>> {
    const httpParams = this.getHttpParams(searchModel);
    return this.httpClient.get<PaginatedResponse<Genre>>(this.apiUrl, {params: httpParams});
  }

  fetchGenresPaginated(
    page: number,
    pageSize: number,
    sortField: string,
    sortDirection: string,
    searchModel?: Genre
  ): Observable<PaginatedResponse<Genre>> {
    let httpParams = this.getHttpParams(searchModel);
    httpParams = httpParams.append("page", page);
    httpParams = httpParams.append("size", pageSize);
    httpParams = httpParams.append("sortField", sortField);
    httpParams = httpParams.append("sortDirection", sortDirection);

    return this.httpClient.get<PaginatedResponse<Genre>>(this.apiUrl, {params: httpParams});
  }

  fetchGenre(id: number): Observable<Genre> {
    return this.httpClient.get<Genre>(`${this.apiUrl}/${id}`);
  }

  insertGenre(genre: Genre) {
    return this.httpClient.post<Genre>(this.apiUrl, {
      name: genre.name,
    }, { observe: 'response' });
  }

  updateGenre(genre: Genre) {
    return this.httpClient.put<Genre>(`${this.apiUrl}/${genre.id}`, {
      name: genre.name,
    }, { observe: 'response' });
  }

  deleteGenre(id: number) {
    return this.httpClient.delete(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  /**
   * Downloads the .xlsx file with list of all the genres.
   */
  downloadXlsxFile(searchModel?: Genre) {
    const httpParams = this.getHttpParams(searchModel);
    this.fileService.downloadXlsxFile(`${this.apiUrl}/xlsx`, "genres.xlsx", httpParams);
  }

  /**
   * Downloads the .pdf file with the details of a genre, given its id.
   */
  downloadPdfFile(genreId: number) {
    this.fileService.downloadPdfFile(`${this.apiUrl}/${genreId}/pdf`, `genre-${genreId}.pdf`);
  }

  private getHttpParams(searchModel?: Genre) {
    let params = new HttpParams();
    if (searchModel?.name) {
      params = params.append("name", searchModel.name);
    }

    return params;
  }
}
