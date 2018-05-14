package com.dupuy.remi.outerspacemanager.models;

/**
 * Created by rdupuy on 14/05/2018.
 */

public class ShipAttack {
    private int shipId;
    private int amount;

    public ShipAttack(int shipId, int amount) {
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
