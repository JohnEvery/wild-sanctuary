package com.every.wildlife.territory.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerritoryRequestDTO {

    private String territoryName;

    private String territoryCode;

    private Double square;

    private String responsiblePersonName;

    private String responsiblePersonPhone;



}
