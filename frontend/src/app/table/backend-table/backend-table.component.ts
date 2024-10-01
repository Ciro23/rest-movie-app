import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {TableField} from "../table-field";
import {SortDirection} from "../sort-direction";
import {Observable} from "rxjs";
import {ActionButtonsComponent} from "../../action-buttons/action-buttons.component";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {PaginatedResponse} from "../../paginated-response";
import {TableComponent} from "../table/table.component";

/**
 * This table uses backend pagination (It has to be fully implemented
 * in the backend).
 */
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
   * Rows must be fetched by the backend page by page.
   */
  @Input()
  getPaginatedRows!: (
    page: number,
    pageSize: number,
    sortField: string,
    sortDirection: SortDirection
  ) => Observable<PaginatedResponse<any>>;

  /** {@link TableComponent.pageSize} */
  @Input() pageSize: number = 10;

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

  /**
   * This is necessary to reload the rows from the backend when
   * the filters are applied. It's not a perfect solution.
   */
  @Input()
  searchModel: any;

  /** {@link TableComponent.rows} */
  paginatedRows: any[] = [];

  currentPage = 1;
  totalCount = 0;

  ngOnInit(): void {
    this.goToPage(this.currentPage);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['searchModel']) {
      this.ngOnInit();
    }
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
    this.getPaginatedRows(page, this.pageSize, this.sortField!.name, this.sortDirection!)
      .subscribe(rows => {
        this.currentPage = page;
        this.paginatedRows = rows.data;
        this.totalCount = rows.totalCount;
      });
  }
}
