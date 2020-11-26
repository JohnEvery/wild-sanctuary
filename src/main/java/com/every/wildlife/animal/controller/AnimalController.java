package com.every.wildlife.animal.controller;

import com.every.wildlife.animal.config.AnimalGroup;
import com.every.wildlife.animal.config.AnimalType;
import com.every.wildlife.animal.controller.dto.AnimalRequestDTO;
import com.every.wildlife.animal.controller.dto.AnimalResponseDTO;
import com.every.wildlife.animal.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnimalController {

    private final AnimalService animalService;


    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/animal")
    public Long saveAnimal(@RequestBody AnimalRequestDTO animalRequestDTO) {
        AnimalGroup animalGroup = animalService.getAnimalGroup(animalRequestDTO.getAnimalGroup());
        AnimalType animalType = animalService.getAnimalType(animalRequestDTO.getAnimalType());
        return animalService.saveAnimal(animalRequestDTO.getAnimalName(), animalType.getAnimalType(),
                animalGroup.getAnimalGroup(), animalRequestDTO.getTerritoryId());
    }

    @GetMapping("/animal/{id}")
    public AnimalResponseDTO getAnimalById(@RequestParam(value = "id") Long animalId) {
        return new AnimalResponseDTO(animalService.getAnimalById(animalId));
    }

    @GetMapping("/animal")
    public List<AnimalResponseDTO> getAllAnimals(@RequestParam(name = "size", required = false,
            defaultValue = "10") Integer size,
                                                 @RequestParam(name = "page", required = false,
                                                         defaultValue = "0") Integer page) {
        return animalService.getAllAnimals(page, size)
                .stream()
                .map(AnimalResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PutMapping("/animal/{id}")
    public Long updateAnimal(@RequestParam(value = "id") Long animalId, String animalName,
                             String animalType, String animalGroup, Long territoryId) {
        return animalService.updateAnimal(animalId, animalName, animalType, animalGroup, territoryId).getId();
    }

    @DeleteMapping("/animals/{id}")
    public AnimalResponseDTO deleteAnimal(@RequestParam(value = "id") Long animalId) {
        return new AnimalResponseDTO(animalService.deleteAnimal(animalId));
    }
}
