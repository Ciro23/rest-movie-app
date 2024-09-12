import {Component, Input} from '@angular/core';
import {TableComponent} from "../../table/table.component";
import {Genre} from "../genre";
import {Router} from "@angular/router";
import {GenreService} from "../genre.service";
import {TableField} from "../../table/table-field";

@Component({
  selector: 'app-genre-table',
  standalone: true,
  imports: [
    TableComponent
  ],
  templateUrl: './genre-table.component.html',
})
export class GenreTableComponent {
  @Input() genres: Genre[] = [];
  @Input() searchModel?: Genre;

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

  delete = (userId: number) => {
    this.genreService.deleteGenre(userId)
      .subscribe(response => {
        if (response.status === 204) {
          this.genres = this.genres.filter(user => user.id !== userId);
        }
      });
  }
}
