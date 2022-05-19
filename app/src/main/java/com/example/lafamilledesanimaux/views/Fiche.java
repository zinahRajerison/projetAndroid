package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.lafamilledesanimaux.R;

public class Fiche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);
        displayVideo();
    }
    public void displayVideo(){
        VideoView view = (VideoView)findViewById(R.id.criAnimal);
        getResources().getIdentifier("FILENAME_WITHOUT_EXTENSION",
                "raw", getPackageName());
        String path = "android.resource://" + getPackageName() + "/" + R.raw.baleine;
        view.setVideoURI(Uri.parse(path));
        view.start();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}