package com.every.wildlife.animal.controller.dto;

import com.every.wildlife.animal.entity.Animal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalResponseDTO {

    private Long id;

    private String animalName;

    private String animalType;

    private String animalGroup;

    private Long territoryId;

    public AnimalResponseDTO(Animal animal) {
        this.id = animal.getId();
        this.animalName = animal.getAnimalName();
        this.animalType = animal.getAnimalType();
        this.animalGroup = animal.getAnimalGroup();
        this.territoryId = animal.getTerritoryId();
    }

}
