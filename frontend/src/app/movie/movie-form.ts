import {Genre} from "../genre/genre";
import {Person} from "../person/person";

/**
 * Model representing movies during creation or editing
 * in HTML forms.
 */
export interface MovieForm {
  id?: number;
  title?: string;
  releaseDate?: Date;
  budget?: number;
  boxOffice?: number;
  runtime?: number;
  overview?: string;
  genres?: Genre[];
  directors?: Person[];
  actors?: Person[];
}
