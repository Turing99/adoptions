package com.p5.adoption.model;

public class CatDTO extends Animal
{
    public CatDTO(String name, String photoUrl, Integer id) {
        super(name, photoUrl,id);
    }

    public void iAmCat(){
        System.out.println("Cats are special!");
    }
    public void speak(){
        //todo inheritance
        System.out.println("Cat speaks!");
    }

}
