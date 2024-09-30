import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Person} from "./person";
import {FileService} from "../file-service";
import {SearchablePerson} from "./searchable-person";
import {Genre} from "../genre/genre";
import {Movie} from "../movie/movie";

@Injectable({providedIn: "root"})
export class PersonService {
  apiUrl = "/api/people";

  constructor(
    private httpClient: HttpClient,
    private fileService: FileService,
  ) {}

  fetchPeople(searchModel?: SearchablePerson): Observable<Person[]> {
    const httpParams = this.getHttpParams(searchModel);
    return this.httpClient.get<Person[]>(this.apiUrl, {params: httpParams});
  }

  fetchPerson(id: number): Observable<Person> {
    return this.httpClient.get<Person>(`${this.apiUrl}/${id}`);
  }

  fetchDirectedMovies(personId: number): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(`${this.apiUrl}/${personId}/directed-movies`);
  }

  fetchStarredMovies(personId: number): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(`${this.apiUrl}/${personId}/starred-movies`);
  }

  insertPerson(person: Person) {
    return this.httpClient.post<Person>(this.apiUrl, {
      name: person.name,
      lastName: person.lastName,
      gender: person.gender,
      birth: person.birth.toISOString().split('T')[0],
    }, { observe: 'response' });
  }

  updatePerson(person: Person) {
    return this.httpClient.put<Person>(`${this.apiUrl}/${person.id}`, {
      name: person.name,
      lastName: person.lastName,
      gender: person.gender,
      birth: person.birth.toISOString().split('T')[0],
    }, { observe: 'response' });
  }

  deletePerson(id: number) {
    return this.httpClient.delete(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  /**
   * Downloads the .xlsx file with list of all the people.
   */
  downloadXlsxFile(searchModel?: SearchablePerson) {
    const httpParams = this.getHttpParams(searchModel);
    this.fileService.downloadXlsxFile(`${this.apiUrl}/xlsx`, "people.xlsx", httpParams);
  }

  /**
   * Downloads the .pdf file with the details of a person, given its id.
   */
  downloadPdfFile(personId: number) {
    this.fileService.downloadPdfFile(`${this.apiUrl}/${personId}/pdf`, `person-${personId}.pdf`);
  }

  private getHttpParams(searchModel?: SearchablePerson) {
    let params = new HttpParams();
    if (searchModel?.name) {
      params = params.append("name", searchModel.name);
    }

    if (searchModel?.gender) {
      params = params.append("gender", searchModel.gender);
    }

    if (searchModel?.birthStart) {
      params = params.append("birthStart", new Date(searchModel.birthStart).toISOString());
    }

    if (searchModel?.birthEnd) {
      params = params.append("birthEnd", new Date(searchModel.birthEnd).toISOString());
    }

    return params;
  }
}
