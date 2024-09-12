export interface Movie {
  id: number;
  title: string;
  releaseDate: Date;
  budget: number;
  boxOffice?: number;
  runtime: number;
  overview: string;
  genreIds: number[];
  directorIds: number[];
  actors: Actor[],
}

// TODO: !!!!!
export interface Actor {actorId: number, roleName: string, castOrder: number}
