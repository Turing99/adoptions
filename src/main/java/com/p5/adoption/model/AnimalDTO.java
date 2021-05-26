package com.p5.adoption.model;

public class AnimalDTO {

    protected Integer id;
    protected String name;
    protected String photoUrl;


    public AnimalDTO(String name, String photoUrl, Integer id){
        this.name = name;
        this.photoUrl = photoUrl;
        this.id = id;

    }

    public AnimalDTO() {

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

    public AnimalDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public AnimalDTO setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public AnimalDTO setId(Integer id) {
        this.id = id;
        return this;
    }
}
