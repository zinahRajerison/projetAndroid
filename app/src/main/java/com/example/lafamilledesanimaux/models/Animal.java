package com.example.lafamilledesanimaux.models;

import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class Animal {

    int id;
    String name;
    String femelle;
    String enfant;

    public Animal(){}

    public Animal(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal(int id, String name, String femelle, String enfant) {
        this.id = id;
        this.name = name;
        this.femelle = femelle;
        this.enfant = enfant;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFemelle() {
        return femelle;
    }

    public void setFemelle(String femelle) {
        this.femelle = femelle;
    }

    public String getEnfant() {
        return enfant;
    }

    public void setEnfant(String enfant) {
        this.enfant = enfant;
    }

    public ArrayList<Animal> getList(){
        ArrayList<Animal> animal = new ArrayList<Animal>();
        /*Animal temp;
        String name = "";
        String path = "";
        for(int i=0; i<25; i++){
            name = "Animal" + i;
            path = "img" + i;
            temp = new Animal(i, path, name);
            animal.add(temp);
        }
        return animal;*/


        try {
            AsyncHttpClient client = new AsyncHttpClient();
            final byte[][] response = new byte[1][1];
            String signupURL = "http://enfant-explorateur.herokuapp.com/findAll";
            client.get(signupURL, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    response[0] = responseBody;
                    try {
                        JSONObject obj = new JSONObject(new String(response[0]));
                        JSONArray first = obj.getJSONArray("data");
                        Log.d("findAllAnimals", first.toString() );
                        Animal temp = new Animal();
                        for (int i = 0; i < first.length(); i++) {
                            JSONObject row = first.getJSONObject(i);
                            temp = new Animal(row.getInt("_id"), row.getString("nom_animal"), row.getString("femelle"), row.getString("enfant"));
                            animal.add(temp);
                            Log.d("findAllAnimals", temp.getFemelle() );
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("findAllAnimals", e.toString());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            // Log.d("findAllAnimals", "changes");
        }catch(Exception e){
            e.printStackTrace();
        }
        return animal;
    }

    public ArrayList<Animal> getListByCriteria(String nom, int iSelectedPays, int iSelectedCat) {
        ArrayList<Animal> animal = new ArrayList<Animal>();
        Log.d("MyMessage", nom + " " + String.valueOf(iSelectedPays) + " " + String.valueOf(iSelectedCat) );
        try {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("name", nom);
            params.put("id_categorie", String.valueOf(iSelectedCat));
            params.put("id_pays", String.valueOf(iSelectedPays));
            RequestParams reqparams = new RequestParams(params);
            AsyncHttpClient client = new AsyncHttpClient();
            String findURL = "http://enfant-explorateur.herokuapp.com/findAnimal";
            client.post(findURL, reqparams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        JSONObject obj = new JSONObject(new String(responseBody));
                        JSONArray first = obj.getJSONArray("data");
                        Log.d("MyMessage", first.toString() );
                        Animal temp = new Animal();
                        for (int i = 0; i < first.length(); i++) {
                            JSONObject row = first.getJSONObject(i);
                            temp = new Animal(row.getInt("_id"), row.getString("nom_animal"));
                            animal.add(temp);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("findAllAnimals", e.toString());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return animal;
    }
}
