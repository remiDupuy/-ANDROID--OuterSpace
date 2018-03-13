package com.dupuy.remi.outerspacemanager.Models;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class Fleet {
    private int size;
    private List<ShipFleet> ships;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ShipFleet> getShips() {
        return ships;
    }

    public void setShips(List<ShipFleet> ships) {
        this.ships = ships;
    }

    public Fleet() {
    }
}
