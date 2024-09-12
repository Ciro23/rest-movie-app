import { Component } from '@angular/core';
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './not-found.component.html',
})
export class NotFoundComponent {

  goBack() {
    window.history.back();
  }
}
