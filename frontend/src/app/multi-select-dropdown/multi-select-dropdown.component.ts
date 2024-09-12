import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgForOf} from "@angular/common";
import {NgbDropdown, NgbDropdownMenu, NgbDropdownToggle} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-multi-select-dropdown',
  standalone: true,
  imports: [
    NgForOf,
    NgbDropdown,
    NgbDropdownMenu,
    NgbDropdownToggle
  ],
  templateUrl: './multi-select-dropdown.component.html',
})
export class MultiSelectDropdownComponent {
  @Input() title!: string;
  @Input() elements: Element[] = [];

  /**
   * The elements to be already checked as selected when the
   * menu initially loads.
   */
  @Input() selectedElementIds: number[] = [];

  /**
   * Emits selected elements to the parent component.
   */
  @Output() selectedElementsChange = new EventEmitter<number[]>();

  onChange(elementId: number): void {
    if (this.selectedElementIds.includes(elementId)) {
      this.selectedElementIds = this.selectedElementIds.filter(id => id !== elementId);
    } else {
      this.selectedElementIds.push(elementId);
    }
    this.selectedElementsChange.emit(this.selectedElementIds);
  }
}

interface Element {
  id: number;
  name: string;
}
