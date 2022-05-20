package com.example.lafamilledesanimaux.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimalFiche {

    @SerializedName("_id")
    @Expose
    private Integer id;
    @SerializedName("nom_animal")
    @Expose
    private String nomAnimal;
    @SerializedName("id_pays")
    @Expose
    private Integer idPays;
    @SerializedName("id_categorie")
    @Expose
    private Integer idCategorie;
    @SerializedName("femelle")
    @Expose
    private String femelle;
    @SerializedName("enfant")
    @Expose
    private String enfant;
    @SerializedName("categorie")
    @Expose
    private Categorie categorie;
    @SerializedName("pays")
    @Expose
    private Pays pays;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public Integer getIdPays() {
        return idPays;
    }

    public void setIdPays(Integer idPays) {
        this.idPays = idPays;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
}
