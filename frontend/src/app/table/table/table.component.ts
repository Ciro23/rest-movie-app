import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {KeyValuePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {TableField} from "../table-field";
import {ConfirmationModalComponent} from "../../confirmation-modal/confirmation-modal.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SortDirection} from "../sort-direction";
import {Observable} from "rxjs";

/**
 * Do not use this component directly, use it through
 * {@link FrontendTableComponent} or {@link BackendTableComponent}
 * based on if you want to use frontend or backend pagination.
 */
@Component({
  selector: 'app-table',
  standalone: true,
  imports: [
    ActionButtonsComponent,
    NgForOf,
    NgIf,
    RouterLink,
    KeyValuePipe,
    NgClass
  ],
  templateUrl: './table.component.html',
})
export class TableComponent {

  /**
   * The actual data to display in the current page of the table.
   */
  @Input()
  rows!: any[];

  /**
   * The total number of rows contained in the table, considering all
   * pages.
   */
  @Input()
  totalCount: number = 0;

  /**
   * Number of rows displayed in each page.
   */
  @Input()
  pageSize: number = 10;

  @Input()
  currentPage: number = 1;

  /**
   * The columns of the table, with their name and callbacks
   * to obtains the values.
   */
  @Input()
  fields!: TableField[];

  @Input()
  sortRows!: (field: TableField, sortDirection?: SortDirection) => void;

  @Input()
  goToPage!: (page: number) => void;

  /**
   * The column currently used to sort the table.
   * If not specified, the table will not be sorted.
   */
  @Input()
  sortField?: TableField;

  /**
   * The sorting order to apply on {@link sortField}.
   * If not specified, the first sorting will be ascending.
   */
  @Input()
  sortDirection?: SortDirection;

  @Input()
  noRowsFoundMessage!: string;

  @Input() onView?: (rowId: number) => void;
  @Input() onEdit?: (rowId: number) => void;
  @Input() onDelete?: (rowId: number) => Observable<boolean>;
  @Input() onCreateNew!: () => void;

  @Input() onDownloadXlsx!: () => void;
  @Input() onDownloadPdf?: (rowId: number) => void;

  constructor(private modalService: NgbModal) {}

  get totalPages() {
    return Math.ceil(this.totalCount / this.pageSize);
  }

  openDownloadXlsxConfirmationDialog() {
    const modalRef = this.modalService.open(ConfirmationModalComponent);
    modalRef.componentInstance.title = "Confirm XLSX download";
    modalRef.componentInstance.message = "Are you sure you want to download the XLSX?";
    modalRef.componentInstance.confirmed.subscribe(() => {
      this.onDownloadXlsx?.();
    });
  }

  getOnView(rowId: number): () => void {
    return () => this.onView?.(rowId);
  }

  getOnEdit(rowId: number): () => void {
    return () => this.onEdit?.(rowId);
  }

  getOnDownloadPdf(rowId: number): () => void {
    return () => this.onDownloadPdf?.(rowId);
  }

  getOnDelete(rowId: number): () => void {
    return () => this.onDelete?.(rowId).subscribe(success => {
      if (success) {
        this.rows = this.rows.filter(row => row.id !== rowId);
        this.totalCount--;
      }
    });
  }

  /**
   * Numbers must be aligned to right when in a table cell
   * to maximise readability.
   */
  isNumber(value: any): boolean {
    return typeof value === 'number';
  }
}
