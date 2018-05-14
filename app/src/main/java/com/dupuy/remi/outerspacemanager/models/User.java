package com.dupuy.remi.outerspacemanager.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by rdupuy on 16/01/2018.
 */
@Table(database = AppDatabase.class)
public class User extends BaseModel {

    @Column
    public String email;

    @PrimaryKey
    public String username;

    @Column
    public String password;

    @Column
    public float gas;

    @Column
    public int gasModifier;

    @Column
    public float minerals;

    @Column
    public int mineralsModifier;

    @Column
    public int points;

    public User() {  }
}
