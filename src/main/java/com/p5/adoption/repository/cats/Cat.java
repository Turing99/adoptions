package com.p5.adoption.repository.cats;

import com.p5.adoption.repository.animals.Animal;

import javax.persistence.Entity;


@Entity
public class  Cat  extends Animal {

    private String purrLevel;

    public Cat() {
    }

    public String getPurrLevel() {
        return purrLevel;
    }

    public Cat setPurrLevel(String purrLevel) {
        this.purrLevel = purrLevel;
        return this;
    }



}
