import {Genre} from "../genre/genre";

export interface SearchableMovie {
  title?: string;
  genres?: Genre[];

  releaseDateStart?: Date;
  releaseDateEnd?: Date;

  budgetStart?: number;
  budgetEnd?: number;

  boxOfficeStart?: number;
  boxOfficeEnd?: number;

  runtimeStart?: number;
  runtimeEnd?: number;
}
