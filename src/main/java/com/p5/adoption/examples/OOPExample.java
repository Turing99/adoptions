package com.p5.adoption.examples;

import com.p5.adoption.model.Animal;
import com.p5.adoption.model.CatDTO;

public class OOPExample {

    public static void main(String[] args) {
        Animal animal = new Animal("Hachi","http://hachi.jpg",1);
        Animal animal1 = new Animal()
                .setName("Mata")
                .setPhotoUrl("photo");

        //Polimorphism= capacitatea unui obiect de a avea mai multe forme

        CatDTO catDTO = new CatDTO("Machi", "photo", 1);
        Animal catAnimal = new CatDTO("Zoro", "photo", 2);
        //st=exprimam interfete, clase abstracte(modelul de sus)
        //dr=specificam implementarile
        catDTO.iAmCat();

        catDTO.speak();
        catAnimal.speak();
    }
}
