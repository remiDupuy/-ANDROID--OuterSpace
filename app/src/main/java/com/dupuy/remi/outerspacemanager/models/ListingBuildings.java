package com.dupuy.remi.outerspacemanager.models;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class ListingBuildings {
    private Integer size;
    private List<Building> buildings;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public ListingBuildings(Integer size, List<Building> buildings) {
        this.size = size;
        this.buildings = buildings;
    }

    public ListingBuildings() {
    }
}
