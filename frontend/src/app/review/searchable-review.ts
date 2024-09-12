import {Movie} from "../movie/movie";
import {User} from "../user/user";
import {SearchableMovie} from "../movie/searchable-movie";
import {SearchableUser} from "../user/searchable-user";

export interface SearchableReview {
  movies?: Movie[];
  users?: User[];

  creationDateStart?: Date;
  creationDateEnd?: Date;

  voteStart?: number;
  voteEnd?: number;
}
