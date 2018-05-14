package com.dupuy.remi.outerspacemanager.models;

import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by rdupuy on 16/01/2018.
 */
public class UserScore extends BaseModel {


    public long points;
    public String username;

    public UserScore(int points, String username) {
        this.points = points;
        this.username = username;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
