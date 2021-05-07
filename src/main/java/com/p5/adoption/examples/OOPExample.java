package com.p5.adoption.examples;

import com.p5.adoption.model.Animal;
import com.p5.adoption.model.Cat;

public class OOPExample {

    public static void main(String[] args) {
        Animal animal = new Animal("Hachi","http://hachi.jpg");
        Animal animal1 = new Animal()
                .setName("Mata")
                .setPhotoUrl("photo");

        //Polimorphism= capacitatea unui obiect de a avea mai multe forme

        Cat cat = new Cat("Machi", "photo");
        Animal catAnimal = new Cat("Zoro", "photo");
        //st=exprimam interfete, clase abstracte(modelul de sus)
        //dr=specificam implementarile
        cat.iAmCat();

        cat.speak();
        catAnimal.speak();
    }
}
