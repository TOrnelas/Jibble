package com.tiago.jibbletesttask;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public class Utils {

    private static final String SHARED_PREF_FILE_NAME = "FIRST_TIME_TAG";

    private static final String FIRST_TIME_TAG = "FIRST_TIME_TAG";

    public static boolean isFirstTime(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean(FIRST_TIME_TAG, true);

        if (isFirstTime) sharedPreferences.edit().putBoolean(FIRST_TIME_TAG, false).apply();

        return isFirstTime;
    }
}