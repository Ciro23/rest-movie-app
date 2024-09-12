import {
  Component,
  ElementRef,
  EventEmitter,
  HostListener,
  Input,
  OnChanges,
  Output,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import { FormControl, ReactiveFormsModule } from "@angular/forms";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import { debounceTime, Observable, of, switchMap } from "rxjs";

/**
 * Provides a text field to search data based on the provided
 * query. It's useful when it's necessary to search across very
 * large amount of data, because no data is loaded until the search
 * query has been written by the user.
 * It supports both single and multiple selections.
 */
@Component({
  selector: 'app-autocomplete-field',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    NgForOf,
    NgClass
  ],
  templateUrl: './autocomplete-field.component.html',
  styleUrl: './autocomplete-field.component.css'
})
export class AutocompleteFieldComponent implements OnChanges {
  @Input() placeholder: string = "";

  /**
   * Useful in case this component is used inside a form.
   */
  @Input() isInvalid: boolean = false;

  /**
   * The behaviour of the auto-completable field slightly changes
   * based on the value of this attribute. If false, the value of
   * the only selectable item is written as the value of the html
   * input field, otherwise all results are listed externally.
   */
  @Input() allowMultipleSelections: boolean = false;

  /**
   * All selected items are stored here in the order they gets
   * inserted.
   * It's also possible to preload some items, from the parent
   * component, to be already selected when this component initializes.
   */
  @Input() selectedItems: any[] = [];

  /**
   * These will always be visible when focusing the search field,
   * when no search has been made.
   */
  @Input() defaultResults: any[] = [];

  /**
   * The display value is what appears on the results menu
   * if the item appears from a search.
   */
  @Input() getDisplayValue!: (item: any) => string;
  @Input() onSearch!: (query: string) => Observable<any[]>;

  /**
   * Selected items are emitted to the parent component each time
   * one gets added or removed. If {@link allowMultipleSelections}
   * is false, then the array will contain one element at best.
   */
  @Output() onSelectedItems: EventEmitter<any[]> = new EventEmitter<any[]>();

  @ViewChild('searchInput') searchInput!: ElementRef;
  searchControl = new FormControl();
  loading: boolean = false;
  isDropdownOpen: boolean = false;

  /**
   * Results produced by {@link onSearch}.
   */
  results: any[] = [];

  /**
   * Keyboard arrows are supported to navigate through results.
   */
  highlightedResultIndex?: number;

  /**
   * No search is made until the written query is longer
   * than this, to prevent tons of results from being loaded
   * by just typing a character.
   */
  private readonly MIN_NUMBER_OF_CHARS: number = 2;

  constructor() {
    this.searchControl.valueChanges.pipe(
      debounceTime(400),
      switchMap((searchTerm: string) => {
        if (searchTerm.length < this.MIN_NUMBER_OF_CHARS) {
          return of({ searchTerm, results: this.defaultResults });
        }

        this.loading = true;
        return this.onSearch(searchTerm).pipe(
          switchMap((results: any[]) => of({ searchTerm, results }))
        );
      })
    ).subscribe(({ searchTerm, results }: { searchTerm: string; results: any[] }) => {
      this.loading = false;

      // When not using multiple selections, a single character change in the
      // search input field is enough to remove the current selection!
      if (!this.allowMultipleSelections && this.selectedItems.length === 1) {
        this.selectedItems = [];
      }

      this.handleResults(results)
      this.isDropdownOpen = this.shouldOpenResultDropdown(searchTerm);
    });

  }

  ngOnChanges(changes: SimpleChanges) {
    if (
      changes['selectedItems']
      && !this.allowMultipleSelections
      && this.selectedItems.length > 0
    ) {
      this.setInputValue(this.selectedItems[0]);
    }

    this.handleResults(this.defaultResults);
  }

  selectItem(item: any) {
    if (this.selectedItems.includes(item)) {
      return;
    }

    this.selectedItems.push(item);
    this.onSelectedItems.emit(this.selectedItems);

    if (!this.allowMultipleSelections) {
      this.setInputValue(item);
      this.isDropdownOpen = false;
      this.highlightedResultIndex = undefined;
      return;
    }

    this.handleResults(this.results)
    this.onSearchFieldFocus();
  }

  removeSelectedItem(item: any) {
    this.selectedItems = this.selectedItems.filter(selected => selected !== item);
    this.onSelectedItems.emit(this.selectedItems);
    this.results = this.defaultResults;
  }

  /**
   * When clicking the area containing the selected items "tags",
   * the search input field should also obtain focus.
   */
  focusSearchInput() {
    this.searchInput.nativeElement.focus();
  }

  onSearchFieldFocus() {
    const inputValue = this.searchControl.value;
    this.isDropdownOpen = this.shouldOpenResultDropdown(inputValue)
  }

  private handleResults(results: any) {
    this.results = this.filterOutSelectedItemsFromResults(results);

    // Initially, the selection must refer to the first result, so
    // that by pressing "enter" it will be able to rapidly select it.
    if (this.results.length > 0) {
      this.highlightedResultIndex = 0;
    }
  }

  /**
   * Already selected items are filtered out from the results,
   * to help preventing them from being selected again.
   */
  private filterOutSelectedItemsFromResults(results: any[]) {
    return results.filter(
      (result: any) => !this.selectedItems.some(
        (selected: any) => selected.id === result.id
      )
    );
  }

  private setInputValue(item: any) {
    let value = this.getDisplayValue(item);
    if (this.allowMultipleSelections) {
      value = "";
    }

    this.searchControl.setValue(value, { emitEvent: false });
  }

  private shouldOpenResultDropdown(searchQuery?: string): boolean {
    if (!this.allowMultipleSelections && this.selectedItems.length === 1) {
      return false;
    }

    if (this.results.length > 0) {
      return true;
    }

    if (!searchQuery) {
      searchQuery = "";
    }
    return searchQuery.length >= this.MIN_NUMBER_OF_CHARS
  }

  @HostListener('keydown', ['$event'])
  private handleKeydown(event: KeyboardEvent) {
    if (!this.isDropdownOpen || this.results.length === 0) {
      return;
    }

    const { key } = event;
    if (key === "Escape") {
      this.isDropdownOpen = false;
      event.preventDefault();
      return;
    }

    if (key === "ArrowDown") {
      this.highlightedResultIndex = (this.highlightedResultIndex! + 1) % this.results.length;
      event.preventDefault();
      return;
    }

    if (key === "ArrowUp") {
      this.highlightedResultIndex = this.highlightedResultIndex! > 0
        ? this.highlightedResultIndex! - 1
        : this.results.length - 1;
      event.preventDefault();
      return;
    }

    if (key === "Enter") {
      if (this.highlightedResultIndex! >= 0 && this.highlightedResultIndex! < this.results.length) {
        const highlightedItem = this.results[this.highlightedResultIndex!];
        this.selectItem(highlightedItem);
      }
      event.preventDefault();
      return;
    }
  }

  @HostListener('document:click', ['$event'])
  private clickOutside(event: Event) {
    const target = event.target as HTMLElement;
    if (!target.closest('.position-relative')) {
      this.isDropdownOpen = false;
    }
  }
}
