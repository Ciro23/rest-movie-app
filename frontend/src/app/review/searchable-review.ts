import {Movie} from "../movie/movie";
import {User} from "../user/user";

export interface SearchableReview {
  movies?: Movie[];
  users?: User[];

  creationDateStart?: Date;
  creationDateEnd?: Date;

  voteStart?: number;
  voteEnd?: number;
}
