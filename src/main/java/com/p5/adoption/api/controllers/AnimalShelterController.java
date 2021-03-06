package com.p5.adoption.api.controllers;

import com.p5.adoption.model.AnimalShelterDTO;
import com.p5.adoption.service.AnimalShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shelters")
public class AnimalShelterController
{
    private final AnimalShelterService shelterService;

    public AnimalShelterController(AnimalShelterService shelterService) {
        this.shelterService = shelterService;
    }


    @GetMapping
    private ResponseEntity<List<AnimalShelterDTO>> getAll()
    {
        return ResponseEntity.ok(shelterService.getAll());
    }

    @GetMapping("{id}")
    private ResponseEntity<AnimalShelterDTO> getShelter(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(shelterService.getShelter(id));
    }

    @PostMapping
    private ResponseEntity<AnimalShelterDTO> createShelter(@Valid @RequestBody AnimalShelterDTO shelterDTO)
    {
        return ResponseEntity.ok(shelterService.createShelter(shelterDTO));
    }

    @PutMapping
    private ResponseEntity<AnimalShelterDTO> updateShelter(@Valid @RequestBody AnimalShelterDTO shelterDTO)
    {
        return ResponseEntity.ok(shelterService.updateShelter(shelterDTO));
    }

}
