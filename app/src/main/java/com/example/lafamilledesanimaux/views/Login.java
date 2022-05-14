package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.example.lafamilledesanimaux.R;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    //proprietes
    private EditText txtName;
    private EditText txtPwd;
    private Button btnLogin;
    private TextView lblLink;
    /**
     * initialisaiton des liens avec les objets graphiqyes
     */
    private void init() {
        txtName = (EditText) findViewById(R.id.txtName);
        txtPwd = (EditText) findViewById(R.id.txtPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        lblLink = (TextView) findViewById(R.id.lblLink);
        submitListener();
    }
    private void submitListener(){
        ((Button) findViewById(R.id.btnLogin)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
//                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();
//                Log.d(tag:"message",msg:"clic ok sur le btn");
                String name = "";
                String pwd = "";
                //recuperation des donnees saisies
                try{
                    name=txtName.getText().toString();
                    pwd=txtName.getText().toString();
                    Log.d("message", "name"+name);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        ((TextView) findViewById(R.id.lblLink)).setOnClickListener(new TextView.OnClickListener(){
            public void onClick(View v){
//                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();
//                Log.d(tag:"message",msg:"clic ok sur le btn");

                try{
                    Intent i = new Intent(getApplicationContext(), SignUp.class);
                    startActivity(i);
                    Log.d("message", "link");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}