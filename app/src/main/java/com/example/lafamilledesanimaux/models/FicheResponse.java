package com.example.lafamilledesanimaux.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FicheResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private int status ;
    @SerializedName("data")
    @Expose
    private AnimalFiche data ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AnimalFiche getData() {
        return data;
    }

    public void setData(AnimalFiche data) {
        this.data = data;
    }
}
