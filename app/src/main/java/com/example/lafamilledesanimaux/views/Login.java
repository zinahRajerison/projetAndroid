package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.controllers.UserController;
import com.example.lafamilledesanimaux.models.LoginResponse;
import com.example.lafamilledesanimaux.models.RetrofitClientInstance;
import com.example.lafamilledesanimaux.models.User;
import com.example.lafamilledesanimaux.models.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        this.control = UserController.getInstance();
    }
    //proprietes
    private EditText txtName;
    private EditText txtPwd;
    private Button btnLogin;
    private TextView lblLink;
    private UserController control;
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

    /**
     * ecoute des evenements de login
     */
    private void submitListener(){
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
        ((Button) findViewById(R.id.btnLogin)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
//                final ProgressDialog progressDialog = new ProgressDialog(Login.this);
//                progressDialog.setCancelable(false); // set cancelable to false
//                progressDialog.setMessage("Chargement"); // set message
//                progressDialog.show(); // show progress dialog
                User user= new User(txtName.getText().toString(),txtPwd.getText().toString());
                UserService userservice = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
                userservice.login(user).enqueue(
                         new Callback<LoginResponse>() {
                             @Override
                             public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                                 progressDialog.dismiss(); //dismiss progress dialog
                                 if(response.body()!= null) {
                                     Log.d("tafiditra",response.body().toString());
    //                                 Log.i(TAG, "post submitted to API." + response.body().toString());
    //                                 Intent intent=new Intent(Login.this,Homes.class);
    //                                 startActivity(intent);
                                 }else {
                                     Toast.makeText(Login.this, "Login ou mots de passe incorrecte", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             @Override
                             public void onFailure(Call<LoginResponse> call, Throwable t) {
                                 // if error occurs in network transaction then we can get the error in this method.
                                 t.printStackTrace();
                                 Log.d("error",t.getStackTrace().toString());
                                 Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                                 progressDialog.dismiss(); //dismiss progress dialog
                             }
                        });
                    }
                });
    }

    /**
     * Fonction save preference
     */
    public void logIn(String name,String pwd)  {
        SharedPreferences sharedPreferences= this.getSharedPreferences("userIdentity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("login", pwd);
        editor.apply();
    }
}