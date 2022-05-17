package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.controllers.UserController;

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

    /**
     * Initilisation des liens avec les objts graphiques
     */
    private void init(){
        txtName = (EditText)findViewById(R.id.personName);
        txtPwd = (EditText)findViewById(R.id.password);
        txtConfirmation = (EditText)findViewById(R.id.passwordConf);
        control.getInstance();
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
                    control.createProfil(name, pwd);
                    Intent homeIntent = new Intent(SignUp.this, List.class);
                    startActivity(homeIntent);
                    finish();
                }else{
                    Toast.makeText(SignUp.this, "Vérifier le mot de passe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}