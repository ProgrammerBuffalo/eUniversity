import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit, OnChanges {

  @Input() allItemsCount: number;
  @Input() lengthOfComponent: number;
  @Input() pageSize: number;
  @Output() pageChange = new EventEmitter<number>();

  pages: number[];
  pageIndex: number;
  allPagesCount: number;
  min: number;
  max: number;

  constructor() {
    this.allItemsCount = 0;
    this.lengthOfComponent = 10;
    this.pageIndex = 0;
    this.pageSize = 0;

    this.pages = [];
    this.allPagesCount = 0;
    this.min = 0;
    this.max = 0;
  }

  ngOnInit(): void {
    // if (this.allItemsCount > this.lengthOfComponent)
    //   this.max = this.lengthOfComponent - 1;
    // else this.max = this.allItemsCount - 1;

    // for (let i = this.min; i < this.max; i++)
    //   this.pages.push(i);
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.allPagesCount = Math.ceil(this.allItemsCount / this.pageSize);

    if (this.allPagesCount > this.lengthOfComponent)
      this.max = this.lengthOfComponent - 1;
    else this.max = this.allPagesCount;

    this.reDrawComponent();
  }

  movePrev() {
    if (this.pageIndex != 0) {
      this.pageIndex--;

      if (this.pageIndex < this.min) {
        this.min--;
        this.max--;
        this.reDrawComponent();
      }

      this.pageChange.emit(this.pageIndex);
    }
  }

  moveNext() {
    if (this.pageIndex != this.allPagesCount - 1) {
      this.pageIndex++;

      if (this.pageIndex >= this.max) {
        this.min++;
        this.max++;
        this.reDrawComponent();
      }

      this.pageChange.emit(this.pageIndex);
    }
  }

  movePage(pageIndex: number) {
    this.pageIndex = pageIndex;
    this.pageChange.emit(this.pageIndex);
  }

  reDrawComponent() {
    this.pages = [];
    for (let i = this.min; i < this.max; i++)
      this.pages.push(i);
  }
}
