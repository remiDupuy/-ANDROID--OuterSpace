package com.dupuy.remi.outerspacemanager.models;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by lledent on 23/01/2018.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 1;
}