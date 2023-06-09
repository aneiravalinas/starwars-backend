package com.trileuco.starwarsapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Page<T> {

    private Integer count;
    private Integer next;
    private Integer previous;
    private List<T> results;

    public Page() {
        this.results = new ArrayList<>();
    }

    public Page(Integer count, Integer next, Integer previous, List<T> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page<?> page = (Page<?>) o;
        return Objects.equals(count, page.count) && Objects.equals(next, page.next) && Objects.equals(previous, page.previous) && Objects.equals(results, page.results);
    }

    @Override
    public String toString() {
        return "Page{" +
                "count=" + count +
                ", next=" + next +
                ", previous=" + previous +
                ", results=" + results +
                '}';
    }
}
