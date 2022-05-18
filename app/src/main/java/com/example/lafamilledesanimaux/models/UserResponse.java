package com.example.lafamilledesanimaux.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("_id")
    @Expose
    private int id;
    @SerializedName("nom_user")
    @Expose
    private String name ;
    @SerializedName("mdp_user")
    @Expose
    private String mdp ;

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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
