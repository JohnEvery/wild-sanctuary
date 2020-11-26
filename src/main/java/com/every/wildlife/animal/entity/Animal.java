package com.every.wildlife.animal.entity;

import com.every.wildlife.animal.config.AnimalGroup;
import com.every.wildlife.animal.config.AnimalType;
import com.every.wildlife.territory.entity.Territory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String animalName;

    private String animalType;

    private String animalGroup;

    private Long territoryId;

    public Animal(String animalName, String animalType, String animalGroup, Long territoryId) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalGroup = animalGroup;
        this.territoryId = territoryId;
    }
}
