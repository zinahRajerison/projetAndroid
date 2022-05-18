package com.example.lafamilledesanimaux.controllers;

import com.example.lafamilledesanimaux.models.Animal;

import java.util.ArrayList;

public final class AnimalController {
    private static AnimalController instance = null;
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();

    /**
     * Constructeur privée
     */
    private AnimalController(){
        super();
    }

    /**
     * Création de l'instance
     * @return instance
     */
    public static final AnimalController getInstance(){
        if(AnimalController.instance == null){
            AnimalController.instance = new AnimalController();
        }
        AnimalController.animalList = new Animal().getList();
        return AnimalController.instance;
    }



}
