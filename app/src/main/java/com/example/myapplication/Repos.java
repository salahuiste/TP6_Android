package com.example.myapplication;

import java.io.Serializable;
import java.util.List;
@SuppressWarnings("serial")
public class Repos implements Serializable {
    private int total_count;
    private boolean incomplete_results;
    private List<Repo> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }
}
