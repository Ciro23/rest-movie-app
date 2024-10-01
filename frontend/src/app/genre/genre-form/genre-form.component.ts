import {Component, OnInit} from '@angular/core';
import {BackButtonComponent} from "../../back-button/back-button.component";
import {FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {Genre} from "../genre";
import {GenreService} from "../genre.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormUtil} from "../../form-util";
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {SaveAndCancelButtonsComponent} from "../../form/save-and-cancel-buttons/save-and-cancel-buttons.component";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-genre-form',
  standalone: true,
    imports: [
        BackButtonComponent,
        FormsModule,
        NgForOf,
        NgIf,
        ReactiveFormsModule,
        NgClass,
        ActionButtonsComponent,
        SaveAndCancelButtonsComponent
    ],
  templateUrl: './genre-form.component.html',
})
export class GenreFormComponent implements OnInit {
  /**
   * If undefined, the form is used to add a new genre, otherwise
   * the existing one is fetched from the backend to be edited.
   */
  readonly genreId?: number;

  genre: Genre = {
    id: 0,
    name: "",
  }

  errorMessages: string[] = [];

  constructor(
    private genreService: GenreService,
    private route: ActivatedRoute,
  ) {
    const id = this.route.snapshot.paramMap.get("id");
    this.genreId = id ? +id : undefined;
  }

  ngOnInit(): void {
    if (this.genreId) {
      this.genreService.fetchGenre(this.genreId).subscribe(genre => {
        this.genre = genre;
      })
    }
  }

  /**
   * The genre is automatically inserted if {@link genreId} is not null
   * or undefined, otherwise it gets updated.
   * @param form
   */
  save(form: NgForm) {
    this.errorMessages = FormUtil.getFormValidationErrors(form);
    if (!form.valid || this.errorMessages.length > 0) {
      return;
    }

    let callable;
    if (this.genreId) {
      callable = this.genreService.updateGenre(this.genre);
    } else {
      callable = this.genreService.insertGenre(this.genre);
    }

    callable.subscribe({
      next: (response) => {
        if (response.status == 200 && response.body) {
          window.history.back();
        }
      }, error: (error: HttpErrorResponse) => {
        const errorResponse = error.error;
        this.errorMessages.push(errorResponse.title);
      }
    });
  }
}
