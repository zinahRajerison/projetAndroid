package com.example.lafamilledesanimaux.models;

import java.util.ArrayList;

public class Animal {

    String img;
    String name;

    public Animal(){}

    public Animal(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Animal> getList(){
        ArrayList<Animal> animal = new ArrayList<Animal>();
        Animal temp;
        String name = "";
        String path = "img1.jpg";
        for(int i=0; i<25; i++){
            name = "Animal" + i;
            temp = new Animal(path, name);
            animal.add(temp);
        }
        return animal;
    }
}
