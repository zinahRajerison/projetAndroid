package com.example.lafamilledesanimaux.models;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lafamilledesanimaux.views.SignUp;
// import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class User {

    private final String URL = "http://enfant-explorateur.herokuapp.com/";
    int id;
    String name;
    String pwd;
    // Gson gson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
        Log.d("message", "Nouvelle inscription");
    }

    public void signup(){
        try {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("name", name);
            params.put("pwd", pwd);
            RequestParams reqparams = new RequestParams(params);
            AsyncHttpClient client = new AsyncHttpClient();

            String signupURL = URL + "signup";
            client.post(signupURL, reqparams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String msg = new String(responseBody);
                    Log.d("OnSuccess", msg + "********************************");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void seLogger(){}
}
