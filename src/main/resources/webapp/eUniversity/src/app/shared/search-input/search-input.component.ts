import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-search-input',
  templateUrl: './search-input.component.html',
  styleUrls: ['./search-input.component.scss', '../input.scss']
})
export class SearchInputComponent implements OnInit {

  @Output() searchChange: EventEmitter<string>;
  @Input() searchText: string;

  constructor() {
    this.searchChange = new EventEmitter<string>();
    this.searchText = '';
  }

  ngOnInit(): void {

  }

  search() {
    this.searchChange.emit(this.searchText);
  }
}
