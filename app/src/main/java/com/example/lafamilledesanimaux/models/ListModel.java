package com.example.lafamilledesanimaux.models;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListModel {

    public ArrayList<Categorie> findAllCat(){
        ArrayList<Categorie> list = new ArrayList<Categorie>();
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            String URL = "http://enfant-explorateur.herokuapp.com/findAllCategory";
            client.get(URL, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        JSONObject obj = new JSONObject(new String(responseBody));
                        JSONArray first = obj.getJSONArray("data");
                        Log.d("MyMessage", first.toString() );
                        Categorie temp = new Categorie();
                        for (int i = 0; i < first.length(); i++) {
                            JSONObject row = first.getJSONObject(i);
                            temp = new Categorie(row.getInt("_id"), row.getString("nom_categorie"));
                            list.add(temp);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Pays> findAllPays(){
        ArrayList<Pays> list = new ArrayList<Pays>();
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            String URL = "http://enfant-explorateur.herokuapp.com/findAllPays";
            client.get(URL, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        JSONObject obj = new JSONObject(new String(responseBody));
                        JSONArray first = obj.getJSONArray("data");
                        Log.d("MyMessage", first.toString() );
                        Pays temp = new Pays();
                        for (int i = 0; i < first.length(); i++) {
                            JSONObject row = first.getJSONObject(i);
                            temp = new Pays(row.getInt("_id"), row.getString("nom_pays"));
                            list.add(temp);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
