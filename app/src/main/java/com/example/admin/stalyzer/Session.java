package com.example.admin.stalyzer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RAJAT JADIA on 27-12-2017.
 */

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx)
    {
        this.ctx = ctx;
        sharedPreferences=ctx.getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public void createLoginSession(String username,String name)
    {
        editor.putBoolean("loggedInmode",true);
        editor.putString("UserName", username);
        editor.putString("NAME_OF_USER",name);
        editor.commit();
    }
    public void logoutUser(){

        editor.putBoolean("loggedInmode",false);
        editor.clear();
        editor.commit();
    }
    public boolean loggedin(){

        return  sharedPreferences.getBoolean("loggedInmode",false);
    }
    public void addUser(String sessionId)
    {
        editor.putString("UserID",sessionId);
    }
}
