package com.example.lafamilledesanimaux.controllers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ModeState {
    Context context;
    SharedPreferences sharedPreferences;

    public ModeState(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    public void setState(boolean b){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("mode", b);
        editor.apply();
    }

    public boolean getState(){
        return sharedPreferences.getBoolean("mode", false);
    }
}
