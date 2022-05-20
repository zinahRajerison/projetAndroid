package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.models.LoginResponse;
import com.example.lafamilledesanimaux.models.RetrofitClientInstance;
import com.example.lafamilledesanimaux.models.User;
import com.example.lafamilledesanimaux.models.UserResponse;
import com.example.lafamilledesanimaux.models.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fiche extends AppCompatActivity {
    VideoView view;
    private Button btnPlay;
    private Button btnStop;
    private TextView txtNameAnimal;
    private TextView txtCategorie;
    private TextView txtPays;
    private TextView txtFemelle;
    private TextView txtEnfant;
    private ImageView imgAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);
        displayVideo();
        init();
    }
    private void init() {
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        txtNameAnimal=(TextView) findViewById(R.id.txtNameAnimal);
        txtCategorie=(TextView) findViewById(R.id.txtCategorie);
        txtPays=(TextView) findViewById(R.id.txtPays);
        txtFemelle=(TextView) findViewById(R.id.txtFemelle);
        txtEnfant=(TextView) findViewById(R.id.txtEnfant);
        imgAnimal=(ImageView) findViewById(R.id.imgAnimal);
        submitListener();
    }
    /**
     * ecoute des evenements de login
     */
    private void submitListener(){
        ((Button) findViewById(R.id.btnPlay)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
               view.start();
            }
        });
        ((Button) findViewById(R.id.btnStop)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                view.stopPlayback();
            }
        });
    }
    public void displayVideo(){
        view = (VideoView)findViewById(R.id.criAnimal);
        getResources().getIdentifier("FILENAME_WITHOUT_EXTENSION",
                "raw", getPackageName());
        String path = "android.resource://" + getPackageName() + "/" + R.raw.baleine;
        view.setVideoURI(Uri.parse(path));
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}