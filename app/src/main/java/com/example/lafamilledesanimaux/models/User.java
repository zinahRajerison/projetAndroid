package com.example.lafamilledesanimaux.models;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lafamilledesanimaux.views.SignUp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class User {

    int id;
    String name;
    String pwd;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
        Log.d("message", "Nouvelle inscription");
    }

    public void signup(){
        String URL = "http://localhost:3000/";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("pwd", pwd);
        JsonObjectRequest jsonobject = new JsonObjectRequest(Request.Method.POST,
                URL,
                new JSONObject(params),
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        Log.d("message", "Nouvelle inscription");
                        /*try{
                            DisplayText.setText(response.getString("message"));
                            Log.d("message", "Nouvelle inscription");
                        }catch (JSONException e){
                            e.printStackTrace();
                        }*/
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // DisplayText.setText("That didn't work!");
                        Log.d("message", "Erreur sur l'inscription");
                    }
                });
    }
    public void seLogger(){
    }
}
