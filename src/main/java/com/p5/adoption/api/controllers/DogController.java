package com.p5.adoption.api.controllers;

import com.p5.adoption.model.DogDTO;
import com.p5.adoption.model.ListDTO;
import com.p5.adoption.service.DogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/dogs")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }


    @GetMapping
    public ResponseEntity<ListDTO<DogDTO>> getAllCats()
    {
        return ResponseEntity.ok(dogService.findAll());
    }

    @PostMapping
    public void addCat(@RequestBody DogDTO dog)
    {
        dogService.addDog(dog);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DogDTO>getDogById(@PathVariable("name") String name)
    {
        return ResponseEntity.ok(dogService.findDog(name));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<DogDTO>getDogById(@PathVariable("id") Integer id)
//    {
//        return ResponseEntity.ok(dogService.findDogById(id));
//    }


}
