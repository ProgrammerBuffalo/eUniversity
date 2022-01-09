export class PaginationDTO {
  search: string;
  index: number
  size: number

  constructor(search: string, index: number, size: number) {
    this.search = search;
    this.index = index;
    this.size = size;
  }
}
