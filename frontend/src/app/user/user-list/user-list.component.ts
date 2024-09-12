import {Component, OnInit} from '@angular/core';
import {UserTableComponent} from "../user-table/user-table.component";
import {NgIf, NgOptimizedImage} from "@angular/common";
import {UserService} from "../user.service";
import {User} from "../user";
import {RouterLink} from "@angular/router";
import {Form, FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {SearchableUser} from "../searchable-user";
import {SearchAndResetButtonsComponent} from "../../form/search-and-reset-buttons/search-and-reset-buttons.component";

@Component({
  selector: 'app-users',
  standalone: true,
    imports: [
        UserTableComponent,
        NgOptimizedImage,
        RouterLink,
        FormsModule,
        ReactiveFormsModule,
        NgIf,
        SearchAndResetButtonsComponent,
    ],
  templateUrl: './user-list.component.html',
})
export class UserListComponent implements OnInit {
  users: User[] = [];

  searchModel: SearchableUser = {};

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.search();
  }

  search() {
    this.userService.fetchUsers(this.searchModel).subscribe(users => {
      this.users = users.sort((a, b) => a.username.localeCompare(b.username));
    });
  }

  get filterOn(): boolean {
    return (
      this.searchModel.username !== undefined && this.searchModel.username !== "" ||
      this.searchModel.email !== undefined && this.searchModel.username !== ""
    );
  }

  resetFilter = () => {
    this.searchModel = {};
    this.search();
  }
}
