import {Component, Input} from '@angular/core';
import {TableComponent} from "../../table/table.component";
import {Person} from "../person";
import {Router} from "@angular/router";
import {PersonService} from "../person.service";
import {SearchablePerson} from "../searchable-person";
import {TableField} from "../../table/table-field";

@Component({
  selector: 'app-person-table',
  standalone: true,
  imports: [
    TableComponent
  ],
  templateUrl: './person-table.component.html',
})
export class PersonTableComponent {
  @Input() people: Person[] = [];
  @Input() searchModel?: SearchablePerson;

  fields: TableField[] = [
    { name: "Name", getRawValue: (person: Person) => person.name },
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

  delete = (userId: number) => {
    this.personService.deletePerson(userId)
      .subscribe(response => {
        if (response.status === 204) {
          this.people = this.people.filter(user => user.id !== userId);
        }
      });
  }
}
