package com.dupuy.remi.outerspacemanager.models.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rdupuy on 16/01/2018.
 */

public class SharedPreferencesHelper {
    public static final String PREFS_NAME = "MyPrefsFile";

    public static void setPrefsName(Context context, String key, String data) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, data);
        editor.apply();
    }

    public static String getPrefsName(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(key, defaultValue);
    }

    public static void deletePrefs(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        settings.edit().remove(key).apply();
    }
}
