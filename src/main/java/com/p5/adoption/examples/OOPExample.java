package com.p5.adoption.examples;

import com.p5.adoption.model.AnimalDTO;
import com.p5.adoption.model.CatDTO;

public class OOPExample {

    public static void main(String[] args) {
        AnimalDTO animal = new AnimalDTO("Hachi","http://hachi.jpg",1);
        AnimalDTO animal1 = new AnimalDTO()
                .setName("Mata")
                .setPhotoUrl("photo");

        //Polimorphism= capacitatea unui obiect de a avea mai multe forme

        CatDTO catDTO = new CatDTO("Machi", "photo", 1);
        AnimalDTO catAnimal = new CatDTO("Zoro", "photo", 2);
        //st=exprimam interfete, clase abstracte(modelul de sus)
        //dr=specificam implementarile
        catDTO.iAmCat();

        catDTO.speak();
        catAnimal.speak();
    }
}
