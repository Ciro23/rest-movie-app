import {Component, Input} from '@angular/core';
import {NgIf} from "@angular/common";

/**
 * Provides a "search" button of type "submit" and a "reset filters"
 * one. Works in a form.
 */
@Component({
  selector: 'app-search-and-reset-buttons',
  standalone: true,
    imports: [
        NgIf
    ],
  templateUrl: './search-and-reset-buttons.component.html',
})
export class SearchAndResetButtonsComponent {
  @Input() isFilterOn: boolean = false;
  @Input() resetFilter!: () => void;
}
