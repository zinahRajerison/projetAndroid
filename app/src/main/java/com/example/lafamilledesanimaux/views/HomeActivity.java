package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.models.User;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        User userprofil = new User("name", "pwd");
        userprofil.signup();
    }
}