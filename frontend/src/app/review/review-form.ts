import {Movie} from "../movie/movie";
import {User} from "../user/user";

export interface ReviewForm {
  id?: number;
  movie?: Movie;
  user?: User;
  creationDate?: Date;
  vote?: number;
  content?: string;
}
