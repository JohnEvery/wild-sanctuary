package com.every.wildlife.animal.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequestDTO {

    private String animalName;

    private Integer animalType;

    private Integer animalGroup;

    private Long territoryId;

}
