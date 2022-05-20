package com.example.lafamilledesanimaux.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pays {
    @SerializedName("_id")
    @Expose
    private int id_pays;
    @SerializedName("nom_pays")
    @Expose
    private String nom_pays ;

    public Pays(int id_pays, String nom_pays) {
        this.id_pays = id_pays;
        this.nom_pays = nom_pays;
    }

    public int getId_pays() {
        return id_pays;
    }

    public void setId_pays(int id_pays) {
        this.id_pays = id_pays;
    }

    public String getNom_pays() {
        return nom_pays;
    }

    public void setNom_pays(String nom_pays) {
        this.nom_pays = nom_pays;
    }
}
