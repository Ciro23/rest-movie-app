import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {TableComponent} from "../../table/table/table.component";
import {Genre} from "../genre";
import {Router} from "@angular/router";
import {GenreService} from "../genre.service";
import {TableField} from "../../table/table-field";
import {BackendTableComponent} from "../../table/backend-table/backend-table.component";
import {SortDirection} from "../../table/sort-direction";
import {catchError, map, Observable, of} from "rxjs";

@Component({
  selector: 'app-genre-table',
  standalone: true,
  imports: [
    TableComponent,
    BackendTableComponent
  ],
  templateUrl: './genre-table.component.html',
})
export class GenreTableComponent {
  @Input() searchModel?: Genre;

  getPaginatedRows = (
    page: number,
    pageSize: number,
    sortField: string,
    sortDirection: SortDirection,
  ) => {
    return this.genreService.fetchGenresPaginated(page, pageSize, sortField, sortDirection, this.searchModel);
  }

  fields: TableField[] = [
    { name: "Name", getRawValue: (genre: Genre) => genre.name },
  ];

  constructor(private genreService: GenreService, private router: Router) {}

  createNew = () => {
    void this.router.navigate(["/genres/new"]);
  }

  edit = (userId: number) => {
    void this.router.navigate([`/genres/${userId}/edit`]);
  }

  downloadPdf = (userId: number) => {
    this.genreService.downloadPdfFile(userId);
  }

  downloadXlsx = () => {
    this.genreService.downloadXlsxFile(this.searchModel);
  }

  delete = (genreId: number): Observable<boolean> => {
    return this.genreService.deleteGenre(genreId).pipe(
      map(response => response.status === 204),
      catchError(() => of(false))
    );
  }
}
