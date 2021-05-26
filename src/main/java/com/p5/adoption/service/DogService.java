package com.p5.adoption.service;

import com.p5.adoption.model.DogDTO;
import com.p5.adoption.model.ListDTO;
import com.p5.adoption.model.adapters.DogAdapter;
import com.p5.adoption.repository.dogs.DogRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DogService {

    private final DogRepository dogRepo;

    public DogService(DogRepository dogRepo) {
        this.dogRepo = dogRepo;
    }


    public void addDog(DogDTO dogDTO) {
        if (dogDTO.getName() == null && dogDTO.getPhotoUrl() == null) {
            throw new RuntimeException("Cat must have a name and a photo!");
        }

        dogRepo.save(DogAdapter.fromDto(dogDTO));

    }

    public ListDTO<DogDTO> findAll() {
        List<DogDTO> dogs = DogAdapter.toListDTO(dogRepo.findAll());
        Long totalCount = dogRepo.count();
        return new ListDTO<DogDTO>(Math.toIntExact(totalCount),dogs);

    }

    public DogDTO findDog(String name)
    {
        if(name == null || name.equals(""))
        {
            throw new RuntimeException("Must specify url!");
        }

        return DogAdapter.toDto(dogRepo.findDogByName(name));
    }

//    public DogDTO findDogById(Integer id)
//    {
//        return DogAdapter.toDto(dogRepo.findDogById(id));
//    }
}
