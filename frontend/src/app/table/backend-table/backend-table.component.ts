import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {TableField} from "../table-field";
import {ConfirmationModalComponent} from "../../confirmation-modal/confirmation-modal.component";
import {compareNullableStrings} from "../../collections";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SortDirection} from "../sort-direction";
import {Observable} from "rxjs";
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {PaginatedResponse} from "../../paginated-response";
import {TableComponent} from "../table.component";

@Component({
  selector: 'app-backend-table',
  standalone: true,
    imports: [
        ActionButtonsComponent,
        NgForOf,
        NgIf,
        NgClass,
        TableComponent
    ],
  templateUrl: './backend-table.component.html',
})
export class BackendTableComponent implements OnInit, OnChanges {
  /**
   * The actual data to display in the table.
   */
  @Input() getRows!: (
    page: number,
    pageSize: number,
    sortField: string,
    sortDirection: SortDirection,
    searchModel: any
  ) => Observable<PaginatedResponse<any>>;

  /**
   * The columns of the table, with their name and callbacks
   * to obtains the values.
   */
  @Input() fields!: TableField[];

  /**
   * The column currently used to sort the table.
   * If not specified, the table will not be sorted.
   */
  @Input() sortField?: TableField;

  /**
   * The sorting order to apply on {@link sortField}.
   * If not specified, the first sorting will be ascending.
   */
  @Input() sortDirection?: SortDirection;

  /**
   * The user message used when no rows are displayed.
   */
  @Input() noRowsFoundMessage!: string;

  @Input() onView?: (rowId: number) => void;
  @Input() onEdit?: (rowId: number) => void;
  @Input() onDelete?: (rowId: number) => Observable<boolean>;
  @Input() onCreateNew!: () => void;

  @Input() onDownloadXlsx!: () => void;
  @Input() onDownloadPdf?: (rowId: number) => void;

  /**
   * Number of rows displayed in each page.
   */
  @Input() pageSize: number = 10;

  @Input() searchModel: any;

  rows: any[] = [];
  currentPage = 1;
  totalCount = 0;

  ngOnInit(): void {
    this.goToPage(this.currentPage);
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.ngOnInit();
  }

  sortRows = (field: TableField, sortDirection?: SortDirection) => {
    if (sortDirection) {
      this.sortDirection = sortDirection;
    } else {
      this.sortDirection = (this.sortField === field && this.sortDirection === 'asc') ? 'desc' : 'asc';
    }
    this.sortField = field;

    this.goToPage(this.currentPage);
  }

  goToPage = (page: number) => {
    this.getRows(page, this.pageSize, this.sortField!.name, this.sortDirection!, this.searchModel)
      .subscribe(rows => {
        this.currentPage = page;
        this.rows = rows.data;
        this.totalCount = rows.totalCount;
      });
  }

  get totalPages() {
    return Math.ceil(this.totalCount / this.pageSize);
  }
}
