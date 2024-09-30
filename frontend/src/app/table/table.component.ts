import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ActionButtonsComponent} from "../action-buttons/action-buttons.component";
import {KeyValuePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {TableField} from "./table-field";
import {ConfirmationModalComponent} from "../confirmation-modal/confirmation-modal.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {compareNullableStrings} from "../collections";

type SortOrder = 'asc' | 'desc';

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
export class TableComponent implements OnChanges {

  /**
   * The actual data to display in the table.
   */
  @Input() rows!: any[];

  /**
   * The columns of the table, with their name and callbacks
   * to obtains the values.
   */
  @Input() fields!: TableField[];

  /**
   * The column currently used to sort the table.
   * If not specified, the table will not be sorted.
   */
  @Input() sortedField?: TableField;

  /**
   * The sorting order to apply on {@link sortedField}.
   * If not specified, the first sorting will be ascending.
   */
  @Input() sortOrder?: SortOrder;

  /**
   * The user message used when no rows are displayed.
   */
  @Input() noRowsFoundMessage!: string;

  @Input() onView?: (rowId: number) => void;
  @Input() onEdit?: (rowId: number) => void;
  @Input() onDelete?: (rowId: number) => void;
  @Input() onCreateNew!: () => void;

  @Input() onDownloadXlsx!: () => void;
  @Input() onDownloadPdf?: (rowId: number) => void;

  /**
   * Number of rows displayed in each page.
   */
  @Input() pageSize: number = 10;
  paginatedRows: any[] = [];

  /**
   * Zero-based index of the available pages.
   */
  currentPage: number = 0;
  totalPages: number = 0;

  constructor(private modalService: NgbModal) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['rows']) {
      // Pagination setup.
      this.totalPages = Math.ceil(this.rows.length / this.pageSize);
      this.paginateRows();

      if (this.sortedField) {
        this.sortRows(this.sortedField, this.sortOrder);
      }
    }
  }

  /**
   * The sorting is made automatically based on the type of data
   * contained in the cells. Supported one are: {@link number},
   * {@link Date}, {@link string}. Unknown data types are converted
   * to strings using {@link Object.toString}.
   * @param field The column to use for sorting.
   * @param sortOrder If not specified, the sorting order is toggled
   * based on the current state.
   */
  sortRows(field: TableField, sortOrder?: SortOrder) {
    if (sortOrder) {
      this.sortOrder = sortOrder;
    } else {
      this.sortOrder = (this.sortedField === field && this.sortOrder === 'asc') ? 'desc' : 'asc';
    }
    this.sortedField = field;

    this.rows.sort((a, b) => {
      const valueA = this.getFieldValue(a, field);
      const valueB = this.getFieldValue(b, field);

      if (this.isNumber(valueA) && this.isNumber(valueB)) {
        return this.sortOrder === 'asc' ? valueA - valueB : valueB - valueA;
      }

      const isValueADate = valueA instanceof Date;
      const isValueBDate = valueB instanceof Date;
      if (isValueADate && isValueBDate) {
        return this.sortOrder === 'asc'
          ? valueA.getTime() - valueB.getTime()
          : valueB.getTime() - valueA.getTime();
      }

      return this.sortOrder === 'asc'
        ? compareNullableStrings(valueA, valueB)
        : compareNullableStrings(valueB, valueA);
    });

    this.paginateRows()
  }

  paginateRows() {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedRows = this.rows.slice(startIndex, endIndex);
  }

  goToPage(page: number) {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      this.paginateRows();
    }
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
    return () => this.onDelete?.(rowId);
  }

  isNumber(value: any): boolean {
    return typeof value === 'number';
  }

  private getFieldValue(row: any, field: TableField) {
    const fieldDefinition = this.fields.find(f => f.name === field.name);
    return fieldDefinition ? fieldDefinition.getRawValue(row) : null;
  }
}
