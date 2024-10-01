import {Component, Input, OnInit} from '@angular/core';
import {User} from "../user";
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {Router, RouterLink} from "@angular/router";
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {UserService} from "../user.service";
import {TableComponent} from "../../table/table.component";
import {SearchableUser} from "../searchable-user";
import {TableField} from "../../table/table-field";
import {FrontendTableComponent} from "../../table/frontend-table/frontend-table.component";
import {catchError, map, Observable, of} from "rxjs";

@Component({
  selector: 'app-user-table',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink,
    ActionButtonsComponent,
    NgIf,
    NgOptimizedImage,
    TableComponent,
    FrontendTableComponent
  ],
  templateUrl: './user-table.component.html',
})
export class UserTableComponent {
  @Input() users: User[] = [];
  @Input() searchModel?: SearchableUser;

  fields: TableField[] = [
    { name: "Username", getRawValue: (user: User) => user.username },
    { name: "Email", getRawValue: (user: User) => user.email }
  ];

  constructor(private userService: UserService, private router: Router) {}

  view = (userId: number) => {
    void this.router.navigate([`/users/${userId}`]);
  }

  createNew = () => {
    void this.router.navigate(["/users/new"]);
  }

  edit = (userId: number) => {
    void this.router.navigate([`/users/${userId}/edit`]);
  }

  downloadPdf = (userId: number) => {
    this.userService.downloadPdfFile(userId);
  }

  downloadXlsx = () => {
    this.userService.downloadXlsxFile(this.searchModel);
  }

  delete = (userId: number): Observable<boolean> => {
    return this.userService.deleteUser(userId).pipe(
      map(response => response.status === 204),
      catchError(() => of(false))
    );
  }
}
