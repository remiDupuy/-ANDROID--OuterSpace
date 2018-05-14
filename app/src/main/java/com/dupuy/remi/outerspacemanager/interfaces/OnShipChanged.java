package com.dupuy.remi.outerspacemanager.interfaces;

import com.dupuy.remi.outerspacemanager.models.ShipFleet;

/**
 * Created by rdupuy on 14/05/2018.
 */

public interface OnShipChanged {
    void onShipChange(ShipFleet ship, int quantity);
}
