package com.p5.adoption.repository.dogs;


import com.p5.adoption.repository.animals.Animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Dog extends Animal {

    private String size;

    public Dog() {
    }

    public String getSize() {
        return size;
    }

    public Dog setSize(String size) {
        this.size = size;
        return this;
    }

}
