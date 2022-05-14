package com.example.lafamilledesanimaux.controllers;

import com.example.lafamilledesanimaux.models.User;

public final class UserController {
    private static UserController instance = null;
    private User userprofil;

    /**
     * Constructeur privée
     */
    private UserController(){
        super();
    }

    /**
     * Création de l'instance
     * @return instance
     */
    public static final UserController getInstance(){
        if(UserController.instance == null){
            UserController.instance = new UserController();
        }
        return UserController.instance;
    }

    public void createProfil(String name, String pwd ){
        userprofil = new User(name, pwd);
    }
    public boolean login(String name,String pwd){
        userprofil = new User(name,pwd);
        userprofil.seLogger();
    }

}
