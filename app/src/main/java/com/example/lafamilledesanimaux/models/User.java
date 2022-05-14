package com.example.lafamilledesanimaux.models;

import android.util.Log;

public class User {

    int id;
    String name;
    String pwd;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
        Log.d("message", "Nouvelle inscription");
    }

    private void signup(){

    }
}
