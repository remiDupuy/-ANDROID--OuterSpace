package com.dupuy.remi.outerspacemanager.models;

import java.util.List;

/**
 * Created by rdupuy on 27/02/2018.
 */

public class ListingSearches {

    private int size;
    private List<Search> searches;


    public ListingSearches(int size, List<Search> searches) {
        this.size = size;
        this.searches = searches;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Search> getSearches() {
        return searches;
    }

    public void setSearches(List<Search> searches) {
        this.searches = searches;
    }
}

