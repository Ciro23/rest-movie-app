import {Component, Input} from '@angular/core';
import {TableComponent} from "../../table/table.component";
import {Router} from "@angular/router";
import {Review} from "../review";
import {SearchableReview} from "../searchable-review";
import {ReviewService} from "../review.service";
import {Movie} from "../../movie/movie";
import {User} from "../../user/user";
import {TableField} from "../../table/table-field";

@Component({
  selector: 'app-review-table',
  standalone: true,
  imports: [
    TableComponent
  ],
  templateUrl: './review-table.component.html',
})
export class ReviewTableComponent {
  @Input() reviews: Review[] = [];
  @Input() searchModel?: SearchableReview;

  fields: TableField[] = [
    { name: "Movie", getRawValue: (review: Review) => review.movie.title },
    { name: "User", getRawValue: (review: Review) => review.user.username },
    { name: "Creation date", getRawValue: (review: Review) => review.creationDate },
    { name: "Vote", getRawValue: (review: Review) => review.vote },
  ];

  constructor(
    private reviewService: ReviewService,
    private router: Router,
  ) {}

  view = (userId: number) => {
    void this.router.navigate([`/reviews/${userId}`]);
  }

  createNew = () => {
    void this.router.navigate(["/reviews/new"]);
  }

  edit = (userId: number) => {
    void this.router.navigate([`/reviews/${userId}/edit`]);
  }

  downloadPdf = (userId: number) => {
    this.reviewService.downloadPdfFile(userId);
  }

  downloadXlsx = () => {
    this.reviewService.downloadXlsxFile(this.searchModel);
  }

  delete = (userId: number) => {
    this.reviewService.deleteReview(userId)
      .subscribe(response => {
        if (response.status === 204) {
          this.reviews = this.reviews.filter(user => user.id !== userId);
        }
      });
  }
}
