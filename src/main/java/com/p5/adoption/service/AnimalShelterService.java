package com.p5.adoption.service;

import com.p5.adoption.model.AnimalShelterDTO;
import com.p5.adoption.model.adapters.AnimalShelterAdapter;
import com.p5.adoption.repository.animals.Animal;
import com.p5.adoption.repository.shelter.AnimalShelter;
import com.p5.adoption.repository.shelter.AnimalShelterRepository;
import org.springframework.stereotype.Service;

@Service
public class AnimalShelterService {


    private final AnimalShelterRepository animalShelterRepository;

    public AnimalShelterService(AnimalShelterRepository animalShelterRepository) {
        this.animalShelterRepository = animalShelterRepository;
    }

    public AnimalShelterDTO getShelter(Integer id) {
        return AnimalShelterAdapter.toDto(animalShelterRepository.getOne(id));
    }

    public AnimalShelterDTO createShelter(AnimalShelterDTO shelterDTO) {

        AnimalShelter animalShelter = AnimalShelterAdapter.fromDto(shelterDTO);

        animalShelter.setId(null);
        for (Animal animal : animalShelter.getAnimals())
        {
            animal.setId(null);
        }

        return AnimalShelterAdapter.toDto(animalShelterRepository.save(animalShelter));
    }
}
