import {Component, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {User} from "../user";
import {DatePipe, NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {BackButtonComponent} from "../../back-button/back-button.component";
import {Review} from "../../review/review";
import {ReviewService} from "../../review/review.service";
import {Movie} from "../../movie/movie";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [
    RouterLink,
    NgOptimizedImage,
    ActionButtonsComponent,
    BackButtonComponent,
    NgForOf,
    DatePipe,
    NgClass,
    NgIf
  ],
  templateUrl: './user-details.component.html',
})
export class UserDetailsComponent implements OnInit {
  user!: User;
  reviews: Review[] = [];

  constructor(
    private userService: UserService,
    private reviewService: ReviewService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    const userId = +this.route.snapshot.paramMap.get('id')!;

    this.userService.fetchUser(userId).subscribe({
      next: (response) => this.user = response,
      error: () => void this.router.navigate(['/404'], { skipLocationChange: true }),
    });

    this.userService.fetchReviewsByUserId(userId).subscribe(reviews => {
      this.reviews = reviews;
    })
  }

  getOnEdit(userId: number): () => void {
    return () => void this.router.navigate([`/users/${userId}/edit`]);
  }

  getOnDownloadPdf(userId: number): () => void {
    return () => this.userService.downloadPdfFile(userId);
  }

  getOnDelete(userId: number): () => void {
    return () => this.userService.deleteUser(userId)
      .subscribe(response => {
        if (response.status === 204) {
          window.history.back();
        }
      });
  }
}
