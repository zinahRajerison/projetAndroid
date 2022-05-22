package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
import com.example.lafamilledesanimaux.models.Favoris;
import com.example.lafamilledesanimaux.models.FavoriteResponse;
import com.example.lafamilledesanimaux.models.FicheResponse;
import com.example.lafamilledesanimaux.models.Id;
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
    private ImageView imgFavoris;
    private int idAnimal;
    private int idUser;
    private AnimalFiche animal;
    private static int splash_time_out = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        final ProgressDialog progressDialog = new ProgressDialog(Fiche.this);
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Chargement..."); // set message
//        progressDialog.show();
        super.onCreate(savedInstanceState);
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run(){
                setContentView(R.layout.activity_fiche);
                init();
//                progressDialog.dismiss();
//            }
//        }, splash_time_out);
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
        imgFavoris=(ImageView) findViewById(R.id.imgFavoris);
        view = (VideoView)findViewById(R.id.criAnimal);
        submitListener();
        idAnimal = getIntent().getIntExtra("idanimal",0);
        SharedPreferences sharedPreferences= Fiche.this.getSharedPreferences("userToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        idUser = Integer.parseInt(sharedPreferences.getString("id", ""));
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
        ((ImageView) findViewById(R.id.imgFavoris)).setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View v){
                if(animal.getFavoris()!=0)
                {
                    Toast.makeText(Fiche.this, "deja un favoris"+idUser, Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("ajoutFavoris", "hhhhhhhey" );
                    Favoris fav= new Favoris(idAnimal,idUser);
                    UserService userservice = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
                    userservice.ajoutFavoris(fav).enqueue(
                            new Callback<FavoriteResponse>() {
                                @Override
                                public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
//                                 progressDialog.dismiss(); //dismiss progress dialog
                                    if(response.body()!= null) {
                                        Log.d("status",String.valueOf(response.body().getStatus()));
                                        finish();
                                        startActivity(getIntent());
                                    }else {
                                        Toast.makeText(Fiche.this, "non insere", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<FavoriteResponse> call, Throwable t) {
                                    // if error occurs in network transaction then we can get the error in this method.
                                    t.printStackTrace();
                                    Log.d("error",t.getStackTrace().toString());
                                    Toast.makeText(Fiche.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                    }

            }
        });
    }

    /**
     * affichage de la video
     * @param fnm
     */
    public void displayVideo(String fnm){
        getResources().getIdentifier("FILENAME_WITHOUT_EXTENSION",
                "raw", getPackageName());
        String path = "android.resource://" + getPackageName() + "/" + "raw/"+fnm;
        view.setVideoURI(Uri.parse(path));
    }

    /**
     * instanciation des donnees a afficher dans la fiche
     */
    public void setDataFiche(){
        final ProgressDialog progressDialog = new ProgressDialog(Fiche.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Chargement"); // set message
        Log.d("idAnimal:",String.valueOf(this.idAnimal));
        UserService userservice = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
        userservice.findById(new Id(this.idAnimal,this.idUser)).enqueue(
                new Callback<FicheResponse>() {
                    @Override
                    public void onResponse(Call<FicheResponse> call, Response<FicheResponse> response) {
//                                 progressDialog.dismiss(); //dismiss progress dialog
                        if(response.body()!= null) {
                             animal=response.body().getData();
                            Toast.makeText(Fiche.this, "idAnimal"+String.valueOf(response.body().getStatus()), Toast.LENGTH_SHORT).show();

                            Log.d("status",String.valueOf(response.body().getStatus()));
                            Log.d("tafiditra",animal.getCategorie().getNom_categorie());
                            txtNameAnimal.setText(" Nom de l'animal : " +animal.getNomAnimal() );
                            txtCategorie.setText("De categorie " +animal.getCategorie().getNom_categorie());
                            txtPays.setText("Pays D'origine : "+animal.getPays().getNom_pays());
                            txtFemelle.setText("Femelle : "+animal.getFemelle());
                            txtEnfant.setText("Enfant : "+animal.getEnfant());

                            String fnm = animal.getNomAnimal().toLowerCase(); //  this is image file name
                            String PACKAGE_NAME = getApplicationContext().getPackageName();
                            int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+fnm , null, null);
                            System.out.println("IMG ID :: "+imgId);
                            System.out.println("PACKAGE_NAME :: "+PACKAGE_NAME);
//    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imgId);
                            imgAnimal.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
                            displayVideo(fnm);
//                           startActivity(intent);
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