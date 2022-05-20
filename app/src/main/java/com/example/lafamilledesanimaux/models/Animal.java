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
    String img;
    String name;

    public Animal(){}

    public Animal(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal(String img, String name) {
        this.id = 0;
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Animal> getList(){
        ArrayList<Animal> animal = new ArrayList<Animal>();
        /*Animal temp;
        String name = "";
        String path = "";
        for(int i=0; i<25; i++){
            name = "Animal" + i;
            path = "img" + i;
            temp = new Animal(path, name);
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
            // Log.d("findAllAnimals", "changes");
        }catch(Exception e){
            e.printStackTrace();
        }
        return animal;
    }

    public ArrayList<Animal> getListByCriteria(String input, String criteria){
        ArrayList<Animal> animal = new ArrayList<Animal>();
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            final byte[][] response = new byte[1][1];
            String signupURL = "http://enfant-explorateur.herokuapp.com/findAnimal";
            client.get(signupURL, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    response[0] = responseBody;
                    //Log.d("findAllAnimals", new String(responseBody));
                    try {
                        JSONObject obj = new JSONObject(new String(response[0]));
                        JSONArray first = obj.getJSONArray("data");
                        Log.d("findAllAnimals", first.toString() );
                        Animal temp = new Animal();
                        for (int i = 0; i < first.length(); i++) {
                            JSONObject row = first.getJSONObject(i);
                            temp = new Animal(row.getInt("_id"), row.getString("nom_animal"));
                            animal.add(temp);
                            // Log.d("findAllAnimals", "haha" + animal.get(i).getName());
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
}
