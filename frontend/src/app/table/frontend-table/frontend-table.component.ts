import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {KeyValuePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {TableField} from "../table-field";
import {SortDirection} from "../sort-direction";
import {compareNullableStrings} from "../../collections";
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {TableComponent} from "../table/table.component";
import {Observable} from "rxjs";

/**
 * This table uses frontend pagination.
 */
@Component({
  selector: 'app-frontend-table',
  standalone: true,
  imports: [
    ActionButtonsComponent,
    NgForOf,
    NgIf,
    RouterLink,
    KeyValuePipe,
    NgClass,
    TableComponent,
  ],
  templateUrl: './frontend-table.component.html',
})
export class FrontendTableComponent implements OnChanges {

  @Input()
  allRows: any[] = [];

  /** {@link TableComponent.pageSize} */
  @Input()
  pageSize: number = 10;

  /** {@link TableComponent.fields} */
  @Input()
  fields!: TableField[];

  /** {@link TableComponent.sortField} */
  @Input()
  sortField?: TableField;

  /** {@link TableComponent.sortDirection} */
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

  /** {@link TableComponent.rows} */
  paginatedRows: any[] = [];

  currentPage: number = 1;

  get totalPages() {
    return Math.ceil(this.allRows.length / this.pageSize);
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['allRows']) {
      if (this.sortField) {
        this.sortRows(this.sortField, this.sortDirection);
      } else {
        this.paginateRows();
      }
    }
  }

  /**
   * The sorting is made automatically based on the type of data
   * contained in the cells. Supported one are: {@link number},
   * {@link Date}, {@link string}. Unknown data types are converted
   * to strings using {@link Object.toString}.
   * @param field The column to use for sorting.
   * @param sortDirection If not specified, the sorting direction is toggled
   * based on the current state.
   */
  sortRows = (field: TableField, sortDirection?: SortDirection) => {
    if (sortDirection) {
      this.sortDirection = sortDirection;
    } else {
      this.sortDirection = (this.sortField === field && this.sortDirection === 'asc') ? 'desc' : 'asc';
    }
    this.sortField = field;

    this.allRows.sort((a, b) => {
      const valueA = this.getFieldValue(a, field);
      const valueB = this.getFieldValue(b, field);

      if (this.isNumber(valueA) && this.isNumber(valueB)) {
        return this.sortDirection === 'asc' ? valueA - valueB : valueB - valueA;
      }

      const isValueADate = valueA instanceof Date;
      const isValueBDate = valueB instanceof Date;
      if (isValueADate && isValueBDate) {
        return this.sortDirection === 'asc'
          ? valueA.getTime() - valueB.getTime()
          : valueB.getTime() - valueA.getTime();
      }

      return this.sortDirection === 'asc'
        ? compareNullableStrings(valueA, valueB)
        : compareNullableStrings(valueB, valueA);
    });

    this.paginateRows()
  }

  goToPage = (page: number) => {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
      this.paginateRows();
    }
  }

  private paginateRows() {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedRows = this.allRows.slice(startIndex, endIndex);
  }

  private isNumber(value: any): boolean {
    return typeof value === 'number';
  }

  private getFieldValue(row: any, field: TableField) {
    const fieldDefinition = this.fields.find(f => f.name === field.name);
    return fieldDefinition ? fieldDefinition.getRawValue(row) : null;
  }
}
