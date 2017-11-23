package com.example.ucsm.officechat;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by WaiYan on 8/2/2016.
 */
public class SharedPreference {

    public static final String PREFS_NAME = "OfficeChat_PREFS";


    // Shared Preferences
    SharedPreferences sharedPreferences;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    SharedPreferences settings;
    SharedPreferences.Editor editor;
    String text;


    public SharedPreference() {
        super();
    }

    // Constructor
    public SharedPreference(Context context) {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_ID = "user_id";


    /**
     * Create login session
     */
    public void createLoginSession(int id) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing email in pref
        editor.putInt(KEY_ID, id);

        // commit changes
        editor.commit();

    }

    public void Logout()
    {
        editor.clear();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }

    public Integer get_user_id(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Integer user_id = settings.getInt(KEY_ID, 0);
        return user_id;
    }

}
