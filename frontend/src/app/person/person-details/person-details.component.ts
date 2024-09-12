import {Component, OnInit} from '@angular/core';
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {BackButtonComponent} from "../../back-button/back-button.component";
import {PersonService} from "../person.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {CurrencyPipe, DatePipe, NgFor, NgIf} from "@angular/common";
import {Genre} from "../../genre/genre";
import {Person} from "../person";
import {Movie} from "../../movie/movie";

@Component({
  selector: 'app-person-details',
  standalone: true,
  imports: [
    ActionButtonsComponent,
    BackButtonComponent,
    NgIf,
    CurrencyPipe,
    DatePipe,
    NgFor,
    RouterLink
  ],
  templateUrl: './person-details.component.html',
})
export class PersonDetailsComponent implements OnInit {
  person!: Person;
  directedMovies: Movie[] = [];
  starredMovies: Movie[] = [];

  constructor(
    private personService: PersonService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    const personId = +this.route.snapshot.paramMap.get('id')!;
    this.personService.fetchPerson(personId).subscribe({
      next: (response) => {
        this.person = response
      },
      error: (_) => {
        void this.router.navigate(['/404'], {skipLocationChange: true})
      },
    });

    this.personService.fetchDirectedMovies(personId)
      .subscribe(movies => {
        this.directedMovies = movies.sort((a, b) => {
          return new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime();
        });
      });

    this.personService.fetchStarredMovies(personId)
      .subscribe(movies => {
        this.starredMovies = movies.sort((a, b) => {
          return new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime();
        });
      });

  }

  getOnEdit(personId: number): () => void {
    return () => void this.router.navigate([`/people/${personId}/edit`]);
  }

  getOnDownloadPdf(personId: number): () => void {
    return () => this.personService.downloadPdfFile(personId);
  }

  getOnDelete(personId: number): () => void {
    return () => this.personService.deletePerson(personId)
      .subscribe(response => {
        if (response.status === 204) {
          window.history.back();
        }
      });
  }
}
