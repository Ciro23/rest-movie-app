import { Routes } from '@angular/router';
import {UserListComponent} from "./user/user-list/user-list.component";
import {UserFormComponent} from "./user/user-form/user-form.component";
import {UserDetailsComponent} from "./user/user-details/user-details.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {MovieListComponent} from "./movie/movie-list/movie-list.component";
import {MovieFormComponent} from "./movie/movie-form/movie-form.component";
import {MovieDetailsComponent} from "./movie/movie-details/movie-details.component";
import {GenreListComponent} from "./genre/genre-list/genre-list.component";
import {GenreFormComponent} from "./genre/genre-form/genre-form.component";
import {ReviewListComponent} from "./review/review-list/review-list.component";
import {ReviewFormComponent} from "./review/review-form/review-form.component";
import {PersonListComponent} from "./person/person-list/person-list.component";
import {PersonFormComponent} from "./person/person-form/person-form.component";
import {PersonDetailsComponent} from "./person/person-details/person-details.component";

export const routes: Routes = [
  { path: "404", component: NotFoundComponent },

  { path: "users", component: UserListComponent },
  { path: "users/new", component: UserFormComponent },
  { path: "users/:id", component: UserDetailsComponent },
  { path: "users/:id/edit", component: UserFormComponent },

  { path: "movies", component: MovieListComponent },
  { path: "movies/new", component: MovieFormComponent },
  { path: "movies/:id", component: MovieDetailsComponent },
  { path: "movies/:id/edit", component: MovieFormComponent },

  { path: "people", component: PersonListComponent },
  { path: "people/new", component: PersonFormComponent },
  { path: "people/:id", component: PersonDetailsComponent },
  { path: "people/:id/edit", component: PersonFormComponent },

  { path: "genres", component: GenreListComponent },
  { path: "genres/new", component: GenreFormComponent },
  { path: "genres/:id", component: GenreFormComponent },
  { path: "genres/:id/edit", component: GenreFormComponent },

  { path: "reviews", component: ReviewListComponent },
  { path: "reviews/new", component: ReviewFormComponent },
  { path: "reviews/:id", component: ReviewFormComponent },
  { path: "reviews/:id/edit", component: ReviewFormComponent },

  { path: "**", component: NotFoundComponent },
];
