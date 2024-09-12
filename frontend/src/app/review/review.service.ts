import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {FileService} from "../file-service";
import {Observable} from "rxjs";
import {SearchableReview} from "./searchable-review";
import {Review} from "./review";
import {User} from "../user/user";
import {Movie} from "../movie/movie";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  apiUrl = "/api/reviews";

  constructor(private httpClient: HttpClient, private fileService: FileService) {}

  fetchReviews(searchModel?: SearchableReview): Observable<Review[]> {
    const httpParams = this.getHttpParams(searchModel);
    return this.httpClient.get<Review[]>(this.apiUrl, {params: httpParams});
  }

  fetchReview(id: number): Observable<Review> {
    return this.httpClient.get<Review>(`${this.apiUrl}/${id}`);
  }

  insertReview(review: Review) {
    return this.httpClient.post<Review>(this.apiUrl, {
      movieId: review.movie.id,
      userId: review.user.id,
      vote: review.vote,
      content: review.content,
    }, { observe: 'response' });
  }

  updateReview(review: Review) {
    return this.httpClient.put<Review>(`${this.apiUrl}/${review.id}`, {
      movieId: review.movie.id,
      userId: review.user.id,
      vote: review.vote,
      content: review.content,
    }, { observe: 'response' });
  }

  deleteReview(id: number) {
    return this.httpClient.delete(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  /**
   * Downloads the .xlsx file with list of all the reviews.
   */
  downloadXlsxFile(searchModel?: SearchableReview) {
    const httpParams = this.getHttpParams(searchModel);
    this.fileService.downloadXlsxFile(`${this.apiUrl}/xlsx`, "reviews.xlsx", httpParams);
  }

  /**
   * Downloads the .pdf file with the details of a review, given its id.
   */
  downloadPdfFile(reviewId: number) {
    this.fileService.downloadPdfFile(`${this.apiUrl}/${reviewId}/pdf`, "reviews.pdf");
  }

  private getHttpParams(searchModel?: SearchableReview) {
    let params = new HttpParams();
    if (searchModel?.movies) {
      searchModel.movies.forEach((movie: Movie) => {
        params = params.append("movieId", movie.id);
      });
    }

    if (searchModel?.users) {
      searchModel.users.forEach((user: User) => {
        params = params.append("userId", user.id);
      });
    }

    if (searchModel?.creationDateStart) {
      params = params.append("creationDateStart", searchModel.creationDateStart.toLocaleDateString());
    }

    if (searchModel?.creationDateEnd) {
      params = params.append("creationDateEnd", searchModel.creationDateEnd.toLocaleDateString());
    }

    if (searchModel?.voteStart) {
      params = params.append("voteStart", searchModel.voteStart);
    }

    if (searchModel?.voteEnd) {
      params = params.append("voteEnd", searchModel.voteEnd);
    }

    return params;
  }
}
