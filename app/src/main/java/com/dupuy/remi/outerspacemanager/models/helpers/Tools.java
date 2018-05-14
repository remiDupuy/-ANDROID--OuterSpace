package com.dupuy.remi.outerspacemanager.models.helpers;

/**
 * Created by rdupuy on 16/04/2018.
 */

public class Tools {
    public static String secondToHumanReadableTime(int second) {
        int h = second/ 3600;
        int m = (second % 3600) / 60;
        int s = second % 60;
        String sh = (h > 0 ? String.valueOf(h) + "h" : "");
        String sm = (m < 10 && m > 0 && h > 0 ? "0" : "") + (m > 0 ? (h > 0 && s == 0 ? String.valueOf(m) : String.valueOf(m) + "m") : "");
        String ss = (s == 0 && (h > 0 || m > 0) ? "" : (s < 10 && (h > 0 || m > 0) ? "0" : "") + String.valueOf(s) + "s");
        return sh + (h > 0 ? " " : "") + sm + (m > 0 ? " " : "") + ss;
    }
}
