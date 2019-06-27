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
    private String X_AUTH_TOKEN = "X_Auth_Token";
    private String EMP_CODE = "empCode";
    private String EMPLOYEE_NAME = "employeeName";

    public SessionManager(Context context) {
        this.ctx = context;
        sharedPreferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setEMPLOYEE_NAME(String name){
        editor.putString(EMPLOYEE_NAME,name);
        editor.commit();
    }

    public String getEMPLOYEE_NAME(){
        return sharedPreferences.getString(EMPLOYEE_NAME,null);
    }

    public void setEMP_CODE(String code){
        editor.putString(EMP_CODE,code);
        editor.commit();
    }

    public String getEMP_Code(){
        return sharedPreferences.getString(EMP_CODE,null);
    }

    public void setLogInStatus(Boolean status){
        editor.putBoolean(SET_LOGIN_STATUS,status);
        editor.commit();

    }

    public void setX_AUTH_TOKEN(String token){
        editor.putString(X_AUTH_TOKEN,token);
        editor.commit();
    }

    public String getX_AUTH_TOKEN(){
        return sharedPreferences.getString(X_AUTH_TOKEN,null);
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
