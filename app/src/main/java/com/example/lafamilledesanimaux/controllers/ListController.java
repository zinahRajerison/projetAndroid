package com.example.lafamilledesanimaux.controllers;

import com.example.lafamilledesanimaux.models.Animal;
import com.example.lafamilledesanimaux.models.Categorie;
import com.example.lafamilledesanimaux.models.ListModel;
import com.example.lafamilledesanimaux.models.Pays;

import java.util.ArrayList;

public final class ListController {
    private static ListController instance = null;
    private static ListModel listModel;
    public static ArrayList<Animal> animalList = new ArrayList<Animal>();
    public static ArrayList<Categorie> categoryList = new ArrayList<Categorie>();
    public static ArrayList<Pays> paysList = new ArrayList<Pays>();

    /**
     * Constructeur privée
     */
    private ListController(){
        super();
    }

    /**
     * Création de l'instance
     * @return instance
     */
    public static final ListController getInstance(){
        if(ListController.instance == null){
            listModel = new ListModel();
            ListController.instance = new ListController();
            ListController.animalList = new Animal().getList();
            ListController.categoryList = listModel.findAllCat();
            ListController.paysList = listModel.findAllPays();
        }
        return ListController.instance;
    }

}
