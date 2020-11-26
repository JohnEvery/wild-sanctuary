package com.every.wildlife.territory.controller;

import com.every.wildlife.territory.controller.dto.TerritoryResponseDTO;
import com.every.wildlife.territory.service.TerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TerritoryController {

    private final TerritoryService territoryService;

    @Autowired
    public TerritoryController(TerritoryService territoryService) {
        this.territoryService = territoryService;
    }

    @PostMapping("/territory")
    public Long createTerritory(@RequestBody TerritoryResponseDTO territoryResponseDTO) {
        return territoryService.saveTerritory(territoryResponseDTO.getTerritoryName(),
                territoryResponseDTO.getTerritoryCode(), territoryResponseDTO.getSquare(),
                territoryResponseDTO.getResponsiblePersonName(), territoryResponseDTO.getResponsiblePersonPhone(),
                territoryResponseDTO.getGroupsInTerritory());
    }

    @GetMapping("/territory")
    public List<TerritoryResponseDTO> getAll(@RequestParam(name = "size", required = false,
            defaultValue = "10") Integer size,
                                             @RequestParam(name = "page", required = false,
                                                     defaultValue = "0") Integer page) {
        return territoryService.getAllTerritories(page, size)
                .stream()
                .map(TerritoryResponseDTO::new)
                .collect(Collectors.toList());
    }
}
