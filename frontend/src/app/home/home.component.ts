import { Component } from '@angular/core';
import {DatePipe} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    DatePipe,
    RouterLink
  ],
  templateUrl: './home.component.html',
})
export class HomeComponent {

}
