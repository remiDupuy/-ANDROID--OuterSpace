package com.dupuy.remi.outerspacemanager.classes;

import java.util.Calendar;

/**
 * Created by rdupuy on 16/04/2018.
 */

public class ProgressBuilding {
    private Calendar cal;
    private int buildingTime;

    public ProgressBuilding(Calendar cal, int buildingTime) {
        this.cal = cal;
        this.buildingTime = buildingTime;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public int getBuildingTime() {
        return buildingTime;
    }

    public void setBuildingTime(int buildingTime) {
        this.buildingTime = buildingTime;
    }
}
