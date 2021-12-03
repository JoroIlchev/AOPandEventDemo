package com.example.apidemo.entity;

import java.util.ArrayList;
import java.util.List;

public class ListOfFoods {
    private List<Food> results;
    private int offset;
    private int number;
    private int totalResults;

    public ListOfFoods() {
        this.results = new ArrayList<>();
    }

    public List<Food> getResults() {
        return results;
    }

    public void setResults(List<Food> results) {
        this.results = results;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
