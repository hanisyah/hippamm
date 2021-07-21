package com.example.hippamm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "LOGIN";
    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID ="id";
    public static final String USERNAME ="username";

    public SessionManager (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }
    public void createLoginSession(String id, String username){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID, id);
        editor.putString(USERNAME, username);
        editor.commit();
    }
    public  void checkLogin(){
        if (!this.isLoggedIn()){
            Intent i = new Intent(_context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        } else {
            Intent i = new Intent(_context, Home.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID,null));
        user.put(USERNAME,sharedPreferences.getString(USERNAME,null));
        return user;
    }
    public void logoutSession(){
        editor.clear();
        editor.commit();
    }
    public boolean isLoggedIn()
    {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public void logout(){
//        SharedPreferences sharedpreferences = _context.getSharedPreferences(SessionManager.PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        _context.startActivity(i);
        ((Home) _context).finish();

    }

}
