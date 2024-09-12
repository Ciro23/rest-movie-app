import { Component } from '@angular/core';
import {BackButtonComponent} from "../../back-button/back-button.component";
import {FormsModule, NgForm} from "@angular/forms";
import {MultiSelectDropdownComponent} from "../../multi-select-dropdown/multi-select-dropdown.component";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {Genre} from "../../genre/genre";
import {GenreService} from "../../genre/genre.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormUtil} from "../../form-util";
import {Person} from "../person";
import {PersonService} from "../person.service";
import {PersonForm} from "../person-form";
import {SaveAndCancelButtonsComponent} from "../../form/save-and-cancel-buttons/save-and-cancel-buttons.component";

@Component({
  selector: 'app-person-form',
  standalone: true,
  imports: [
    BackButtonComponent,
    FormsModule,
    MultiSelectDropdownComponent,
    NgForOf,
    NgIf,
    NgClass,
    SaveAndCancelButtonsComponent
  ],
  templateUrl: './person-form.component.html',
})
export class PersonFormComponent {

  /**
   * If undefined, the form is used to add a new user, otherwise
   * the existing one is fetched from the backend to be edited.
   */
  readonly personId?: number;

  personForm: PersonForm = {}
  errorMessages: string[] = [];

  constructor(
    private personService: PersonService,
    private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    const id = this.route.snapshot.paramMap.get("id");
    this.personId = id ? +id : undefined;
  }

  ngOnInit(): void {
    if (this.personId) {
      this.personService.fetchPerson(this.personId).subscribe(person => {
        this.personForm = this.domainToForm(person);
      });
    }
  }

  save(form: NgForm) {
    this.errorMessages = FormUtil.getFormValidationErrors(form);
    if (!form.valid || this.errorMessages.length > 0) {
      return;
    }

    const person = this.formToDomain(this.personForm);
    let callable;
    if (this.personId) {
      callable = this.personService.updatePerson(person);
    } else {
      callable = this.personService.insertPerson(person);
    }

    callable.subscribe({
      next: (response) => {
        if (response.status == 200 && response.body) {
          window.history.back();
        }
      }, error: (error) => {
        console.error(error);
        this.errorMessages.push("Something went wrong");
      }
    });
  }

  get birth() {
    if (!this.personForm.birth) {
      return "";
    }

    return this.personForm.birth.toISOString().split('T')[0];
  }

  set birth(value: string) {
    if (!value || isNaN(Date.parse(value))) {
      this.personForm.birth = undefined;
      return;
    }

    this.personForm.birth = new Date(value);
  }

  private domainToForm(person: Person): PersonForm {
    return {
      id: person.id,
      name: person.name,
      gender: person.gender,
      birth: new Date(person.birth),
    }
  }

  private formToDomain(personForm: PersonForm): Person {
    return {
      id: personForm.id!,
      name: personForm.name!,
      gender: personForm.gender!,
      birth: personForm.birth!,
    }
  }
}
