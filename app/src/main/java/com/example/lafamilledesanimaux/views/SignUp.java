package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.controllers.ListController;
import com.example.lafamilledesanimaux.controllers.UserController;
import com.example.lafamilledesanimaux.models.NotificationReceiver;
import com.example.lafamilledesanimaux.models.User;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    // properties
    private EditText txtName;
    private EditText txtPwd;
    private EditText txtConfirmation;
    private UserController control;
    private ListController controlAnimal;

    /**
     * Initilisation des liens avec les objets graphiques
     */
    private void init(){
        txtName = (EditText)findViewById(R.id.personName);
        txtPwd = (EditText)findViewById(R.id.password);
        txtConfirmation = (EditText)findViewById(R.id.passwordConf);
        control.getInstance();
        // controlAnimal.getInstance();
        submitListener();
    }

    /**
     * Ecoute evenement sur bouton s'inscrire
     */
    private void submitListener(){
        ((Button) findViewById(R.id.submitbutton)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name = txtName.getText().toString();
                String pwd = txtPwd.getText().toString();
                String conf = txtConfirmation.getText().toString();

                if(pwd.compareTo(conf) == 0){
                    // control.createProfil(name, pwd);
                    Log.d("profil", name);
                    User userprofil = new User(name, pwd);
                    userprofil.signup();
                    Toast.makeText(SignUp.this, "Maintenant connecte toi pour partir à l'aventure!", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(SignUp.this, Login.class);
                    NotificationReceiver notif = new NotificationReceiver();
                    String text = "Bienvenue dans le royaume des animaux " + name + ", connecte toi pour partir à l'aventure avec nous";
                    notif.showNotification(SignUp.this, "Enfant explorateur", text, homeIntent);
                    startActivity(homeIntent);
                    finish();
                }else{
                    Toast.makeText(SignUp.this, "Vérifier le mot de passe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}