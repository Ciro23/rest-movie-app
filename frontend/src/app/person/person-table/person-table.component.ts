import {Component, Input} from '@angular/core';
import {TableComponent} from "../../table/table.component";
import {Person} from "../person";
import {Router} from "@angular/router";
import {PersonService} from "../person.service";
import {SearchablePerson} from "../searchable-person";
import {TableField} from "../../table/table-field";
import {FrontendTableComponent} from "../../table/frontend-table/frontend-table.component";
import {catchError, map, Observable, of} from "rxjs";

@Component({
  selector: 'app-person-table',
  standalone: true,
  imports: [
    TableComponent,
    FrontendTableComponent
  ],
  templateUrl: './person-table.component.html',
})
export class PersonTableComponent {
  @Input() people: Person[] = [];
  @Input() searchModel?: SearchablePerson;

  fields: TableField[] = [
    { name: "Name", getRawValue: (person: Person) => person.name },
    { name: "Last name", getRawValue: (person: Person) => person.lastName },
    { name: "Gender", getRawValue: (person: Person) => person.gender },
    { name: "Birth", getRawValue: (person: Person) => person.birth }
  ];

  constructor(private personService: PersonService, private router: Router) {}

  view = (userId: number) => {
    void this.router.navigate([`/people/${userId}`]);
  }

  createNew = () => {
    void this.router.navigate(["/people/new"]);
  }

  edit = (userId: number) => {
    void this.router.navigate([`/people/${userId}/edit`]);
  }

  downloadPdf = (userId: number) => {
    this.personService.downloadPdfFile(userId);
  }

  downloadXlsx = () => {
    this.personService.downloadXlsxFile(this.searchModel);
  }

  delete = (personId: number): Observable<boolean> => {
    return this.personService.deletePerson(personId).pipe(
      map(response => response.status === 204),
      catchError(() => of(false))
    );
  }
}
