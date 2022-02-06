package com.website.eUniversity.model.dto;

import java.util.List;

public class PaginatedListDTO<T> {
    private List<T> items;

    private Integer allItemsCount;

    public PaginatedListDTO() {

    }

    public List<T> getItems() {
        return items;
    }

    public PaginatedListDTO<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public Integer getAllItemsCount() {
        return allItemsCount;
    }

    public PaginatedListDTO<T> setAllItemsCount(Integer allItemsCount) {
        this.allItemsCount = allItemsCount;
        return this;
    }
}
