import {Component, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {GenreTableComponent} from "../genre-table/genre-table.component";
import {Genre} from "../genre";
import {GenreService} from "../genre.service";
import {SearchAndResetButtonsComponent} from "../../form/search-and-reset-buttons/search-and-reset-buttons.component";

@Component({
  selector: 'app-genre-list',
  standalone: true,
    imports: [
        FormsModule,
        GenreTableComponent,
        NgIf,
        ReactiveFormsModule,
        SearchAndResetButtonsComponent,
    ],
  templateUrl: './genre-list.component.html',
})
export class GenreListComponent implements OnInit {
  genres: Genre[] = [];
  searchModel: Genre = {
    id: 0,
    name: "",
  };

  constructor(private genreService: GenreService) {}

  ngOnInit(): void {
    this.search();
  }

  search() {
    this.genreService.fetchGenres(this.searchModel).subscribe(genres => {
      this.genres = genres;
    })
  }

  get filterOn(): boolean {
    return (
      this.searchModel.name !== ""
    );
  }

  resetFilter = () => {
    this.searchModel = {
      id: 0,
      name: "",
    };
    this.search();
  }
}
