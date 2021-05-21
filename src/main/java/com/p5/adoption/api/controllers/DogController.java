package com.p5.adoption.api.controllers;



import com.p5.adoption.repository.dogs.Dog;
import com.p5.adoption.service.DogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dogs")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }


    @GetMapping
    public ResponseEntity<List<Dog>> getAllCats()
    {
        List<Dog> dogList = dogService.findAll();
        return ResponseEntity.ok(dogList);
    }

    @PostMapping
    public void addCat(@RequestBody Dog dog)
    {
        dogService.addDog(dog);
    }

    @GetMapping("/{url}")
    public ResponseEntity<Dog>getCatByName(@PathVariable("url") String url)
    {
        return ResponseEntity.ok(dogService.findDog(url));
    }


}
