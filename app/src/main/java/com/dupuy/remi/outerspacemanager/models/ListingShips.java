package com.dupuy.remi.outerspacemanager.models;

import java.util.List;

/**
 * Created by rdupuy on 27/02/2018.
 */

public class ListingShips {
    private double currentUserMinerals;
    private double currentUserGas;
    private int size;

    private List<Ship> ships;

    public ListingShips(double currentUserMinerals, double currentUserGas, int size, List<Ship> ships) {
        this.currentUserMinerals = currentUserMinerals;
        this.currentUserGas = currentUserGas;
        this.size = size;
        this.ships = ships;
    }

    public double getCurrentUserMinerals() {
        return currentUserMinerals;
    }

    public void setCurrentUserMinerals(double currentUserMinerals) {
        this.currentUserMinerals = currentUserMinerals;
    }

    public double getCurrentUserGas() {
        return currentUserGas;
    }

    public void setCurrentUserGas(double currentUserGas) {
        this.currentUserGas = currentUserGas;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
