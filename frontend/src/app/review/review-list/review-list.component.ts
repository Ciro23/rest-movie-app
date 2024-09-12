import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {UserTableComponent} from "../../user/user-table/user-table.component";
import {User} from "../../user/user";
import {SearchableUser} from "../../user/searchable-user";
import {UserService} from "../../user/user.service";
import {Review} from "../review";
import {ReviewService} from "../review.service";
import {SearchableReview} from "../searchable-review";
import {ReviewTableComponent} from "../review-table/review-table.component";
import {AutocompleteFieldComponent} from "../../autocomplete-field/autocomplete-field.component";
import {MovieService} from "../../movie/movie.service";
import {SearchableMovie} from "../../movie/searchable-movie";
import {Movie} from "../../movie/movie";
import {SearchAndResetButtonsComponent} from "../../form/search-and-reset-buttons/search-and-reset-buttons.component";

@Component({
  selector: 'app-review-list',
  standalone: true,
    imports: [
        FormsModule,
        NgIf,
        UserTableComponent,
        ReviewTableComponent,
        AutocompleteFieldComponent,
        SearchAndResetButtonsComponent
    ],
  templateUrl: './review-list.component.html',
})
export class ReviewListComponent implements OnInit {
  reviews: Review[] = [];
  searchModel: SearchableReview = {};

  constructor(
    private reviewService: ReviewService,
    private movieService: MovieService,
    private userService: UserService,
  ) {}

  ngOnInit(): void {
    this.search();
  }

  search() {
    this.reviewService.fetchReviews(this.searchModel).subscribe(reviews => {
      this.reviews = reviews;
    });
  }

  searchMoviesByTitle = (title: string) => {
    const searchableMovie: SearchableMovie = {
      title: title,
    }
    return this.movieService.fetchMovies(searchableMovie);
  }

  getMovieTitle = (movie: Movie) => {
    return movie.title;
  }

  onSelectMovies(movies: Movie[]) {
    this.searchModel.movies = movies;
  }

  getUserUsername = (user: User) => {
    return user.username;
  }

  searchUsersByUsername = (username: string) => {
    const searchableUser: SearchableUser = {
      username: username,
    }
    return this.userService.fetchUsers(searchableUser);
  }

  onSelectUsers(users: User[]) {
    this.searchModel.users = users;
  }

  get selectedMovies() {
    return this.getSelectedItems(this.searchModel.movies);
  }

  get selectedUsers() {
    return this.getSelectedItems(this.searchModel.users);
  }

  get filterOn(): boolean {
    return (
      this.searchModel.movies !== undefined && this.searchModel.movies.length > 0 ||
      this.searchModel.users !== undefined && this.searchModel.users.length > 0 ||
      this.searchModel.creationDateStart !== undefined ||
      this.searchModel.creationDateEnd !== undefined ||
      this.searchModel.voteStart !== undefined ||
      this.searchModel.voteEnd !== undefined
    )
  }

  resetFilter =() => {
    this.searchModel = {}
    this.search();
  }

  private getSelectedItems(items?: any[]) {
    if (!items) {
      return [];
    }

    if (items.length > 0) {
      return items;
    }

    return [];
  }
}
