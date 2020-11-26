package com.every.wildlife.territory.controller.dto;

import com.every.wildlife.territory.entity.Territory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TerritoryResponseDTO {

    private Long territoryId;

    private String territoryName;

    private String territoryCode;

    private Double square;

    private String responsiblePersonName;

    private String responsiblePersonPhone;

    private List<GroupDTO> groupsInTerritory;

    public TerritoryResponseDTO(Long territoryId, String territoryName, String territoryCode,
                                Double square, String responsiblePersonName, String responsiblePersonPhone,
                                List<GroupDTO> groupsInTerritory) {
        this.territoryId = territoryId;
        this.territoryName = territoryName;
        this.territoryCode = territoryCode;
        this.square = square;
        this.responsiblePersonName = responsiblePersonName;
        this.responsiblePersonPhone = responsiblePersonPhone;
        this.groupsInTerritory = groupsInTerritory;
    }

    public TerritoryResponseDTO(Territory territory) {
        this.territoryId = territory.getId();
        this.territoryName = territory.getTerritoryName();
        this.territoryCode = territory.getTerritoryCode();
        this.square = territory.getSquare();
        this.responsiblePersonName = territory.getResponsiblePersonName();
        this.responsiblePersonPhone = territory.getResponsiblePersonPhone();
        this.groupsInTerritory = territory.getGroupDTOS();
    }
}
