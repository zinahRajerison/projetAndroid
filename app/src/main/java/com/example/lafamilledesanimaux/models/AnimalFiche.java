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
    @SerializedName("categoriedetails")
    @Expose
    private Categoriedetails categoriedetails;
    @SerializedName("paysdetails")
    @Expose
    private Paysdetails paysdetails;

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

    public Categoriedetails getCategoriedetails() {
        return categoriedetails;
    }

    public void setCategoriedetails(Categoriedetails categoriedetails) {
        this.categoriedetails = categoriedetails;
    }

    public Pays getPaysdetails() {
        return paysdetails;
    }

    public void setPaysdetails(Pays paysdetails) {
        this.paysdetails = paysdetails;
    }

}
