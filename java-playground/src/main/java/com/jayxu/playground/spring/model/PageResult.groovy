package com.jayxu.playground.spring.model

public class PageResult<T> {
    List<T> data
    Long total

    public PageResult(List<T> data = [], Long total = null) {
        this.data = data
        this.total = total
    }
}
