package com.dupuy.remi.outerspacemanager.Models;

/**
 * Created by rdupuy on 09/04/2018.
 */

public class ShipSend {
    private int shipId;
    private int amount;

    public ShipSend(int shipId, int amount) {
        this.shipId = shipId;
        this.amount = amount;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
