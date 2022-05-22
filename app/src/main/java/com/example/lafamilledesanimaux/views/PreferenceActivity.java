package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.controllers.ModeState;

public class PreferenceActivity extends AppCompatActivity {

    private Switch switchbtn;
    ModeState modeState;
    private TextView favoriteView;
    private TextView deconnexionView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        init();
    }

    private void init(){
        switchbtn = findViewById(R.id.switch1);
        favoriteView = (TextView) findViewById(R.id.textView19);
        deconnexionView = (TextView) findViewById(R.id.textView20);
        modeState = new ModeState(this);
        if(modeState.getState()){
            switchbtn.setChecked(true);
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        listener();
    }

    private void listener() {
        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    modeState.setState(true);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    modeState.setState(false);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        favoriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do something
                Intent homeIntent = new Intent(PreferenceActivity.this, List.class);
                SharedPreferences sharedPreferences= PreferenceActivity.this.getSharedPreferences("userToken", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String id = sharedPreferences.getString("id", "");
                homeIntent.putExtra("iduser", id);
                Log.d("findAllAnimals", String.valueOf(homeIntent.getIntExtra("iduser", 0)));
                startActivity(homeIntent);
                finish();
            }
        });
        deconnexionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do something
            }
        });
    }
}