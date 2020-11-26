package com.every.wildlife.animal.service;

import com.every.wildlife.animal.config.AnimalGroup;
import com.every.wildlife.animal.config.AnimalType;
import com.every.wildlife.animal.entity.Animal;
import com.every.wildlife.animal.repository.AnimalRepository;
import com.every.wildlife.exceptions.AnimalNotFoundException;
import com.every.wildlife.territory.controller.dto.GroupDTO;
import com.every.wildlife.territory.entity.Territory;
import com.every.wildlife.territory.service.TerritoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final TerritoryService territoryService;

    public AnimalService(AnimalRepository animalRepository, TerritoryService territoryService) {
        this.animalRepository = animalRepository;
        this.territoryService = territoryService;
    }

    public Long saveAnimal(String animalName, String animalType, String animalGroup,
                           Long territoryId) {
        Animal animal = new Animal(animalName, animalType, animalGroup, territoryId);
        animalRepository.save(animal);
        List<GroupDTO> groupDTOList = groupsOnTerritory(territoryId);
        Territory territory = territoryService.getTerritoryById(territoryId);
        territoryService.updateTerritory(territory.getId(), territory.getTerritoryName(),
                territory.getTerritoryCode(), territory.getSquare(), territory.getResponsiblePersonName(),
                territory.getResponsiblePersonPhone(), groupDTOList);
        return animal.getId();
    }

    public Animal getAnimalById(Long animalId) {
        return animalRepository.findById(animalId).orElseThrow(()
                -> new AnimalNotFoundException("Animal with id: " + animalId + " not found."));
    }

    public List<Animal> getAllAnimals(Integer  page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return animalRepository.findAll(pageable).toList();
    }

    public List<Animal> getAllByTerritoryId(Long territoryId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return animalRepository.findAllByTerritoryId(territoryId, pageable).orElseThrow(()
                -> new AnimalNotFoundException("Unable to find animals on territory with id: " + territoryId))
                .toList();
    }

    public List<Animal> getAllByType(Integer typeAnimal, Integer page, Integer size) {
        AnimalType animalType = getAnimalType(typeAnimal);
        Pageable pageable = PageRequest.of(page, size);
        return animalRepository.findAllByAnimalType(animalType.getAnimalType(), pageable).orElseThrow(()
                -> new AnimalNotFoundException("Unable to find animals in " + animalType.getAnimalType() + " group"))
                .toList();
    }

    public Animal updateAnimal(Long animalId, String animalName, String animalType, String animalGroup,
                               Long territoryId) {
        Animal animal = new Animal(animalName, animalType, animalGroup, territoryId);
        animal.setId(animalId);
        return animalRepository.save(animal);
    }

    public Animal deleteAnimal(Long animalId) {
        Animal animal = getAnimalById(animalId);
        animalRepository.delete(animal);
        return animal;
    }

    public AnimalGroup getAnimalGroup(Integer numberOfGroup) {
        AnimalGroup[] animalGroups = AnimalGroup.values();
        numberOfGroup -= 1;
        return animalGroups[numberOfGroup];
    }

    public AnimalType getAnimalType(Integer numberOfType) {
        AnimalType[] animalTypes = AnimalType.values();
        numberOfType -= 1;
        return animalTypes[numberOfType];
    }

    public List<GroupDTO> groupsOnTerritory(Long territoryId) {
        List<Animal> animals = getAllByTerritoryId(territoryId);
        return groupDTO(animals);
    }

    private List<Animal> getAllByTerritoryId(Long territoryId) {
        return animalRepository.findAllByTerritoryId(territoryId).orElse(new ArrayList<>());
    }

    private List<GroupDTO> groupDTO(List<Animal> animals) {
        List<GroupDTO> groupDTOList = new ArrayList<>();
        for (Animal animal : animals) {
            String animalGroupName = animal.getAnimalGroup();
            Integer groupNumber = getGroupNumber(animalGroupName);
            AnimalGroup animalGroup = getAnimalGroupService(groupNumber);
            Long animalsScoreInGroup = animalsScoreInGroup(animalGroup);
            GroupDTO groupDTO = new GroupDTO(animalGroupName, animalsScoreInGroup);
            groupDTOList.add(groupDTO);
        }
        return groupDTOList;
    }

    private Long animalsScoreInGroup(AnimalGroup animalGroup) {
        long count;
        List<Animal> all = animalRepository.findAll();
        count = all.stream().filter(animal -> animal.getAnimalGroup().equals(animalGroup.toString())).count();
        return count;
    }

    private Integer getGroupNumber(String animalGroup) {
        List<AnimalGroup> animalGroups = Arrays.asList(AnimalGroup.values());
        AnimalGroup group = animalGroups.stream().filter(animal
                -> animal.getAnimalGroup().equals(animalGroup)).findAny().orElseThrow(()
                -> new AnimalNotFoundException("Error"));

        return animalGroups.indexOf(group);

    }

    private AnimalGroup getAnimalGroupService(Integer numberOfGroup) {
        AnimalGroup[] animalGroups = AnimalGroup.values();
        return animalGroups[numberOfGroup];
    }

}
