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
import com.example.lafamilledesanimaux.models.AnimalFiche;
import com.example.lafamilledesanimaux.models.FicheResponse;
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
    private int idAnimal;

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
        idAnimal = getIntent().getIntExtra("idanimal",0);
        Log.d("idAnimal:",String.valueOf(this.idAnimal));
        setDataFiche();
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

    public void setDataFiche(){
        final ProgressDialog progressDialog = new ProgressDialog(Fiche.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Chargement"); // set message
        UserService userservice = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
        userservice.findById(this.idAnimal).enqueue(
                new Callback<FicheResponse>() {
                    @Override
                    public void onResponse(Call<FicheResponse> call, Response<FicheResponse> response) {
//                                 progressDialog.dismiss(); //dismiss progress dialog
                        if(response.body()!= null) {
                            AnimalFiche animal=response.body().getData();
                            Toast.makeText(Fiche.this, "idAnimal"+String.valueOf(response.body().getStatus()), Toast.LENGTH_SHORT).show();

                            Log.d("status",String.valueOf(response.body().getStatus()));
//                            Log.d("tafiditra",animal.getCategorie().getNom_categorie());
//                            Intent intent=new Intent(Login.this,Menu.class);
//                            startActivity(intent);
                        }else {
                            Toast.makeText(Fiche.this, "Login ou mots de passe incorrecte", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FicheResponse> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("error", t.getStackTrace().toString());
                        Toast.makeText(Fiche.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss(); //dismiss progress dialog
                    }
                });
    }
}