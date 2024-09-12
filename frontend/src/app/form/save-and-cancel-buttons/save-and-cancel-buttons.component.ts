import { Component } from '@angular/core';

/**
 * Provides a "save" button of type "submit" and a "cancel"
 * one. Works in a form.
 */
@Component({
  selector: 'app-save-and-cancel-buttons',
  standalone: true,
  imports: [],
  templateUrl: './save-and-cancel-buttons.component.html',
})
export class SaveAndCancelButtonsComponent {

  cancel() {
    window.history.back();
  }
}
