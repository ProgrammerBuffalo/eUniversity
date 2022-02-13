export class PaginationDTO {
  search: string;
  pageIndex: number
  pageSize: number

  constructor(index: number, size: number, search: string,) {
    this.pageIndex = index;
    this.pageSize = size;
    this.search = search;
  }
}
