package com.barbaro.cursoandroid.utils;

import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String fromDateTimeFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public static String fromDateTimeFormat2(Date date){
        return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(date);
    }

    public static Date fromDateTimeFormat(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static String getPreference(SharedPreferences preferences, String key){
        return preferences.getString(key, "");
    }

    public static void setPreference(SharedPreferences preference, String key, String value){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
