package com.p5.adoption.service;

import com.p5.adoption.repository.dogs.Dog;
import com.p5.adoption.repository.dogs.DogRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DogService {

    private final DogRepository dogRepo;

    public DogService(DogRepository dogRepo) {
        this.dogRepo = dogRepo;
    }


    public void addDog(Dog dog) {
        if (dog.getName() == null && dog.getUrl() == null) {
            throw new RuntimeException("Cat must have a name and a photo!");
        }

        Dog dogToSave = new Dog()
                .setName(dog.getName())
                .setUrl(dog.getUrl());

        dogRepo.save(dogToSave);

    }

    public List<Dog> findAll() {
        return dogRepo.findAll();
    }

    public Dog findDog(String url)
    {
        if(url == null || url.equals(""))
        {
            throw new RuntimeException("Must specify url!");
        }

        return dogRepo.findDogByUrl(url);
    }
}
