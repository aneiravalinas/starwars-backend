package com.trileuco.starwarsapi.model.swapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PageSwapi<T> {
    private Integer count;
    private String next;
    private String previous;
    private List<T> results;

    public PageSwapi() {
        results = new ArrayList<>();
    }

    public PageSwapi(Integer count, String next, String previous, List<T> results) {
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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
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
        PageSwapi<?> pageSwapi = (PageSwapi<?>) o;
        return Objects.equals(count, pageSwapi.count) && Objects.equals(next, pageSwapi.next) && Objects.equals(previous, pageSwapi.previous) && Objects.equals(results, pageSwapi.results);
    }

    @Override
    public String toString() {
        return "Page{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
