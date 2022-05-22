package com.example.lafamilledesanimaux.models;

public class Favoris {
    private int idanimal;
    private int iduser;

    public int getId_animal() {
        return idanimal;
    }

    public void setId_animal(int id_animal) {
        this.idanimal = idanimal;
    }

    public int getId_user() {
        return iduser;
    }

    public void setId_user(int id_user) {
        this.iduser = id_user;
    }

    public Favoris(int id_animal, int id_user) {
        this.idanimal = id_animal;
        this.iduser = id_user;
    }
}
