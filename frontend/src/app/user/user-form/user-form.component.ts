import {Component, OnInit} from '@angular/core';
import {
  FormsModule,
  NgForm,
  ReactiveFormsModule,
} from "@angular/forms";
import {User} from "../user";
import {UserService} from "../user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {BackButtonComponent} from "../../back-button/back-button.component";
import {FormUtil} from "../../form-util";
import {SaveAndCancelButtonsComponent} from "../../form/save-and-cancel-buttons/save-and-cancel-buttons.component";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-user-form',
  standalone: true,
    imports: [
        FormsModule,
        NgIf,
        ReactiveFormsModule,
        NgForOf,
        BackButtonComponent,
        NgClass,
        SaveAndCancelButtonsComponent
    ],
  templateUrl: './user-form.component.html',
})
export class UserFormComponent implements OnInit {

  /**
   * If undefined, the form is used to add a new user, otherwise
   * the existing one is fetched from the backend to be edited.
   */
  readonly userId?: number;

  user: User = {
    id: 0,
    username: "",
    email: "",
    password: "",
  }

  errorMessages: string[] = [];

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
  ) {
    const id = this.route.snapshot.paramMap.get("id");
    this.userId = id ? +id : undefined;
  }

  ngOnInit(): void {
    if (this.userId) {
      this.userService.fetchUser(this.userId).subscribe(user => {
        this.user = user;
      })
    }
  }

  /**
   * The user is automatically inserted if {@link userId} is not null
   * or undefined, otherwise it gets updated.
   * @param form
   */
  save(form: NgForm) {
    this.errorMessages = FormUtil.getFormValidationErrors(form);
    if (!form.valid || this.errorMessages.length > 0) {
      return;
    }

    let callable;
    if (this.userId) {
      callable = this.userService.updateUser(this.user);
    } else {
      callable = this.userService.insertUser(this.user);
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
