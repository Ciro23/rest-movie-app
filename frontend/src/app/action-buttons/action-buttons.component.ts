import {Component, Input} from '@angular/core';
import {NgIf, NgOptimizedImage} from "@angular/common";
import {ConfirmationModalComponent} from "../confirmation-modal/confirmation-modal.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

/**
 * Provides useful action buttons to interact with some data.
 * To hide a certain action button, just leave the respective
 * callback to null.
 */
@Component({
  selector: 'app-action-buttons',
  standalone: true,
  imports: [
    NgIf,
    NgOptimizedImage
  ],
  templateUrl: './action-buttons.component.html',
})
export class ActionButtonsComponent {
  @Input() onView?: () => void;
  @Input() onEdit?: () => void;
  @Input() onDownloadPdf?: () => void;
  @Input() onDelete?: () => void;

  constructor(private modalService: NgbModal) {}

  openDownloadPdfConfirmationDialog() {
    const modalRef = this.modalService.open(ConfirmationModalComponent);
    modalRef.componentInstance.title = "Confirm PDF download";
    modalRef.componentInstance.message = "Are you sure you want to download the PDF?";
    modalRef.componentInstance.confirmed.subscribe(() => {
      this.onDownloadPdf?.();
    });
  }

  openDeleteConfirmationDialog() {
    const modalRef = this.modalService.open(ConfirmationModalComponent);
    modalRef.componentInstance.title = "Confirm deletion";
    modalRef.componentInstance.message = "Are you sure you want to delete this item?";
    modalRef.componentInstance.confirmed.subscribe(() => {
      this.onDelete?.();
    });
  }
}
