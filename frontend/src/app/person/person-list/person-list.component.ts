import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {MultiSelectDropdownComponent} from "../../multi-select-dropdown/multi-select-dropdown.component";
import {NgIf} from "@angular/common";
import {Genre} from "../../genre/genre";
import {GenreService} from "../../genre/genre.service";
import {PersonTableComponent} from "../person-table/person-table.component";
import {Person} from "../person";
import {SearchablePerson} from "../searchable-person";
import {PersonService} from "../person.service";
import {SearchAndResetButtonsComponent} from "../../form/search-and-reset-buttons/search-and-reset-buttons.component";

@Component({
  selector: 'app-person-list',
  standalone: true,
    imports: [
        FormsModule,
        PersonTableComponent,
        MultiSelectDropdownComponent,
        NgIf,
        SearchAndResetButtonsComponent,
    ],
  templateUrl: './person-list.component.html',
})
export class PersonListComponent {
  people: Person[] = [];
  searchModel: SearchablePerson = {};

  genres: Genre[] = [];

  constructor(
    private personService: PersonService,
    private genreService: GenreService
  ) {}

  ngOnInit(): void {
    this.search();
  }

  search() {
    this.personService.fetchPeople(this.searchModel).subscribe(people => {
      this.people = people;
    })
    this.genreService.fetchGenres().subscribe(genres => {
      this.genres = genres.data;
    })
  }

  get filterOn(): boolean {
    return (
      this.searchModel.name !== undefined && this.searchModel.name !== "" ||
      this.searchModel.gender !== undefined ||
      this.searchModel.birthStart !== undefined ||
      this.searchModel.birthEnd !== undefined
    );
  }

  resetFilter = () => {
    this.searchModel = {};
    this.search();
  }
}
