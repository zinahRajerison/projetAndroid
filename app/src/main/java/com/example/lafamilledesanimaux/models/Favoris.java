package com.example.lafamilledesanimaux.models;

public class Favoris {
    private int id_animal;
    private int id_user;

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Favoris(int id_animal, int id_user) {
        this.id_animal = id_animal;
        this.id_user = id_user;
    }
}
