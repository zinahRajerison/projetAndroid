package com.example.lafamilledesanimaux.models;

public class Id {
    private int id;
    private int iduser;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Id(int id, int iduser) {
        this.id = id;
        this.iduser = iduser;
    }
}
