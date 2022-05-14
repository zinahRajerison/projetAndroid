package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.lafamilledesanimaux.R;

public class MainActivity extends AppCompatActivity {

    private static int splash_time_out = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, Login.class);
                startActivity(homeIntent);
                finish();
            }
        }, splash_time_out);
    }
}