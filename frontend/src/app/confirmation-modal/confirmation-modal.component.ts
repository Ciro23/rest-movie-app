import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-confirmation-modal',
  standalone: true,
  imports: [],
  templateUrl: './confirmation-modal.component.html',
})
export class ConfirmationModalComponent {
  @Input() title: string = "";
  @Input() message: string = "";
  @Output() confirmed = new EventEmitter<void>();

  constructor(public activeModal: NgbActiveModal) {}

  confirm() {
    this.confirmed.emit();
    this.activeModal.close();
  }

  cancel() {
    this.activeModal.dismiss();
  }
}
