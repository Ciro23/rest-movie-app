import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {forkJoin, map, Observable} from "rxjs";
import {User} from "./user";
import {FileService} from "../file-service";
import {SearchableUser} from "./searchable-user";
import {Movie} from "../movie/movie";
import {ReviewService} from "../review/review.service";
import {SearchableMovie} from "../movie/searchable-movie";
import {Review} from "../review/review";

@Injectable({providedIn: "root"})
export class UserService {
  apiUrl = "/api/users";

  constructor(
    private httpClient: HttpClient,
    private fileService: FileService,
  ) {}

  fetchUsers(searchModel?: SearchableUser): Observable<User[]> {
    const httpParams = this.getHttpParams(searchModel);
    return this.httpClient.get<User[]>(this.apiUrl, {params: httpParams});
  }

  fetchUser(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/${id}`);
  }

  fetchReviewsByUserId(userId: number) {
    return this.httpClient.get<Review[]>(`${this.apiUrl}/${userId}/reviews`);
  }

  insertUser(user: User) {
    return this.httpClient.post<User>(this.apiUrl, {
      username: user.username,
      email: user.email,
      password: user.password,
    }, { observe: 'response' });
  }

  updateUser(user: User) {
    return this.httpClient.put<User>(`${this.apiUrl}/${user.id}`, {
      username: user.username,
      email: user.email,
      password: user.password,
    }, { observe: 'response' });
  }

  deleteUser(id: number) {
    return this.httpClient.delete(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  /**
   * Downloads the .xlsx file with list of all the users.
   */
  downloadXlsxFile(searchModel?: SearchableUser) {
    const httpParams = this.getHttpParams(searchModel);
    this.fileService.downloadXlsxFile(`${this.apiUrl}/xlsx`, "users.xlsx", httpParams);
  }

  /**
   * Downloads the .pdf file with the details of a user, given its id.
   */
  downloadPdfFile(userId: number) {
    this.fileService.downloadPdfFile(`${this.apiUrl}/${userId}/pdf`, `user-${userId}.pdf`);
  }

  private getHttpParams(searchModel?: SearchableUser) {
    let params = new HttpParams();
    if (searchModel?.username) {
      params = params.append("username", searchModel.username);
    }

    if (searchModel?.email) {
      params = params.append("email", searchModel.email);
    }

    return params;
  }
}
