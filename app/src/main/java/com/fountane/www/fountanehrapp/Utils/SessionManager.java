package com.fountane.www.fountanehrapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    Context ctx;
    private static final String PREF_NAME = "FountaneHR";
    private String SET_LOGIN_STATUS = "login_status";

    public SessionManager(Context context) {
        this.ctx = context;
        sharedPreferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setLogInStatus(Boolean status){
        editor.putBoolean(SET_LOGIN_STATUS,status);
        editor.commit();

    }

    public boolean getLogInStatus(){
        return sharedPreferences.getBoolean(SET_LOGIN_STATUS,false);
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        editor.commit();
    }


}
