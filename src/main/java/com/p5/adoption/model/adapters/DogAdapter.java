package com.p5.adoption.model.adapters;

import com.p5.adoption.model.DogDTO;
import com.p5.adoption.repository.dogs.Dog;

import java.util.ArrayList;
import java.util.List;

public class DogAdapter {

    public static Dog fromDto(DogDTO dogDTO)
    {
        if(dogDTO.getName().equals("")){
            dogDTO.setName("Generic cat");
        }
        return (Dog) new Dog().setName(dogDTO.getName()).setPhotoUrl(dogDTO.getPhotoUrl());
    }

    public static DogDTO toDto(Dog dog)
    {
        return new DogDTO(dog.getName(), dog.getPhotoUrl(), dog.getId());
    }


    public static List<DogDTO> toListDTO(List<Dog> dogList)
    {
        List<DogDTO> dtoList = new ArrayList<>();

        dogList.forEach(dog -> dtoList.add(toDto(dog)));
        return dtoList;
    }

}
