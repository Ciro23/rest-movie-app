import {Component, OnInit} from '@angular/core';
import {BackButtonComponent} from "../../back-button/back-button.component";
import {FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";
import {FormUtil} from "../../form-util";
import {Review} from "../review";
import {ReviewService} from "../review.service";
import {ReviewForm} from "../review-form";
import {Movie} from "../../movie/movie";
import {AutocompleteFieldComponent} from "../../autocomplete-field/autocomplete-field.component";
import {SearchableMovie} from "../../movie/searchable-movie";
import {SearchableUser} from "../../user/searchable-user";
import {User} from "../../user/user";
import {UserService} from "../../user/user.service";
import {MovieService} from "../../movie/movie.service";
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {SaveAndCancelButtonsComponent} from "../../form/save-and-cancel-buttons/save-and-cancel-buttons.component";
import {ErrorResponse} from "../../error-response";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-review-form',
  standalone: true,
  imports: [
    BackButtonComponent,
    FormsModule,
    NgForOf,
    NgIf,
    ReactiveFormsModule,
    AutocompleteFieldComponent,
    ActionButtonsComponent,
    NgClass,
    SaveAndCancelButtonsComponent
  ],
  templateUrl: './review-form.component.html',
})
export class ReviewFormComponent implements OnInit {

  /**
   * If undefined, the form is used to add a new review, otherwise
   * the existing one is fetched from the backend to be edited.
   */
  readonly reviewId?: number;

  reviewForm: ReviewForm = {
    creationDate: new Date(),
  };
  editMode: boolean = false;
  errorMessages: string[] = [];

  constructor(
    private reviewService: ReviewService,
    private movieService: MovieService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    const id = this.route.snapshot.paramMap.get("id");
    this.reviewId = id ? +id : undefined;
  }

  ngOnInit(): void {
    this.editMode = this.router.url.includes('/edit') || this.router.url.includes('/new');

    if (this.reviewId) {
      this.reviewService.fetchReview(this.reviewId).subscribe(review => {
        this.reviewForm = this.domainToForm(review);
      })
    }
  }

  /**
   * The review is automatically inserted if {@link reviewId} is not null
   * or undefined, otherwise it gets updated.
   * @param form
   */
  save(form: NgForm) {
    this.errorMessages = FormUtil.getFormValidationErrors(form);

    if (!this.reviewForm.movie) {
      this.errorMessages.push("\"Movie\" is required");
    }

    if (!this.reviewForm.user) {
      this.errorMessages.push("\"User\" is required");
    }

    if (!form.valid || this.errorMessages.length > 0) {
      return;
    }

    const review = this.formToDomain(this.reviewForm);
    let callable;
    if (this.reviewId) {
      callable = this.reviewService.updateReview(review);
    } else {
      callable = this.reviewService.insertReview(review);
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

  get creationDate() {
    if (!this.reviewForm.creationDate) {
      return "";
    }
    return this.reviewForm.creationDate.toISOString().split('T')[0];
  }

  set creationDate(value: string) {
    this.reviewForm.creationDate = new Date(value);
  }

  getMovieTitle = (movie: Movie) => {
    return movie.title;
  }

  searchMoviesByTitle = (title: string) => {
    const searchableMovie: SearchableMovie = {
      title: title,
    }
    return this.movieService.fetchMovies(searchableMovie);
  }

  onSelectMovie(movies: Movie[]) {
    if (movies.length === 0) {
      this.reviewForm.movie = undefined;
    } else {
      this.reviewForm.movie = movies[0];
    }
  }

  getUserEmail = (user: User) => {
    return user.email;
  }

  searchUsersByEmail = (email: string) => {
    const searchableUser: SearchableUser = {
      email: email,
    }
    return this.userService.fetchUsers(searchableUser);
  }

  onSelectUser(users: User[]) {
    if (users.length === 0) {
      this.reviewForm.user = undefined;
    } else {
      this.reviewForm.user = users[0];
    }
  }

  edit = () => {
    void this.router.navigate([`/reviews/${this.reviewId}/edit`]);
  }

  downloadPdf = () => {
    this.reviewService.downloadPdfFile(this.reviewId!);
  }

  delete = () => {
    this.reviewService.deleteReview(this.reviewId!)
      .subscribe(response => {
        if (response.status === 204) {
          window.history.back();
        }
      });
  }

  private domainToForm(review: Review): ReviewForm {
    return {
      id: review.id,
      movie: review.movie,
      user: review.user,
      creationDate: new Date(review.creationDate),
      vote: review.vote,
      content: review.content,
    };
  }

  private formToDomain(reviewForm: ReviewForm): Review {
    return {
      id: reviewForm.id!,
      movie: reviewForm.movie!,
      user: reviewForm.user!,
      creationDate: reviewForm.creationDate!,
      vote: reviewForm.vote!,
      content: reviewForm.content!,
    };
  }
}
