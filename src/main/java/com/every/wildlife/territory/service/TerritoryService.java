package com.every.wildlife.territory.service;

import com.every.wildlife.exceptions.TerritoryNotFounException;
import com.every.wildlife.territory.controller.dto.GroupDTO;
import com.every.wildlife.territory.entity.Territory;
import com.every.wildlife.territory.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerritoryService {

    private final TerritoryRepository territoryRepository;

    @Autowired
    public TerritoryService(TerritoryRepository territoryRepository) {
        this.territoryRepository = territoryRepository;
    }

    public Territory getTerritoryById(Long territoryId) {
        return territoryRepository.findById(territoryId).orElseThrow(()
                -> new TerritoryNotFounException("Territory with id: " + territoryId + " not fount"));
    }

    public Long saveTerritory(String territoryName, String territoryCode, Double square,
                              String responsiblePersonName, String responsiblePersonPhone, List<GroupDTO> groupDTOS) {
        Territory territory = new Territory(territoryName, territoryCode, square,
                responsiblePersonName,responsiblePersonPhone, groupDTOS);
        return territoryRepository.save(territory).getId();
    }

    public Territory updateTerritory(Long territoryId, String territoryName, String territoryCode, Double square,
                                     String responsiblePersonName, String responsiblePersonPhone,
                                     List<GroupDTO> groupDTOS) {
        Territory territory = new Territory(territoryName, territoryCode, square, responsiblePersonName,
                responsiblePersonPhone, groupDTOS);
        territory.setId(territoryId);
        return territoryRepository.save(territory);
    }

    public List<Territory> getAllTerritories(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return territoryRepository.findAll(pageable).toList();
    }

}
