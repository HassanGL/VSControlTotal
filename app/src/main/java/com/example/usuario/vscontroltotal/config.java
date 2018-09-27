package com.example.usuario.vscontroltotal;

import android.content.Context;
import android.content.SharedPreferences;

public class config {
    private final String SHARED_PREFS_FILE = "HMPrefs";
    private final String KEY_WS = "";

    private Context mContext;

    public config(Context context){
        mContext = context;
    }
    private SharedPreferences getSettings(){
        return mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
    }

    public String getWebservice(){
        return getSettings().getString(KEY_WS, "");
    }

    public void setWebservice(String ws){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_WS, ws );
        editor.apply();
    }

}
