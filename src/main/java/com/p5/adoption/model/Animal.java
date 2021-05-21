package com.p5.adoption.model;

public class Animal {

    protected Integer id;
    protected String name;
    protected String photoUrl;


    public Animal(String name, String photoUrl, Integer id){
        this.name = name;
        this.photoUrl = photoUrl;
        this.id = id;

    }

    public Animal() {

    }

    public void speak(){
        //todo inheritance
        System.out.println("Animal speaks!");
    }
    protected void walk(){
        //todo
        System.out.println("Animal walks!");
    }



    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Animal setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Animal setId(Integer id) {
        this.id = id;
        return this;
    }
}
