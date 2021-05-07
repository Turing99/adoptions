package com.p5.adoption.model;

public class Cat extends Animal
{
    public Cat(String name, String photoUrl) {
        super(name, photoUrl);
    }

    public void iAmCat(){
        System.out.println("Cats are special!");
    }
    public void speak(){
        //todo inheritance
        System.out.println("Cat speaks!");
    }

}
