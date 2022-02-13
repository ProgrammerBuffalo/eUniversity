export class PaginatedList<T> {
  items: T[];
  allItemsCount: number;

  constructor(list: T[], allItemsCount: number) {
    this.items = list;
    this.allItemsCount = allItemsCount;
  }
}
