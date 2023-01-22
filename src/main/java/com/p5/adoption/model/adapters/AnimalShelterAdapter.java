package com.p5.adoption.model.adapters;


import com.p5.adoption.model.AnimalShelterDTO;
import com.p5.adoption.repository.shelter.AnimalShelter;

import java.util.ArrayList;
import java.util.List;

public class AnimalShelterAdapter
{

    public static AnimalShelter fromDto(AnimalShelterDTO shelterDTO)
    {
        AnimalShelter animalShelter = new AnimalShelter()
                .setAddress(shelterDTO.getAddress())
                .setName(shelterDTO.getName())
                .setId(shelterDTO.getId())
                .setAnimals(AnimalAdapter.fromDto(shelterDTO.getAnimals()));
        return animalShelter;
    }

    public static AnimalShelterDTO toDto(AnimalShelter shelter)
    {
        return new AnimalShelterDTO()
                .setAddress(shelter.getAddress())
                .setId(shelter.getId())
                .setName(shelter.getName())
                .setAnimals(AnimalAdapter.toListDto(shelter.getAnimals()));
    }

    public static List<AnimalShelterDTO> toListDTO(List<AnimalShelter> animals)
    {
        List<AnimalShelterDTO> dtos = new ArrayList<>();
        for(AnimalShelter animalShelter: animals)
        {
            dtos.add(toDto((animalShelter)));
        }
        return dtos;
    }
}