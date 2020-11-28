package com.every.wildlife.animal.repository;

import com.every.wildlife.animal.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<List<Animal>> findAllByTerritoryId(Long territoryId);

    Optional<Page<Animal>> findAllByTerritoryId(Long territoryId, Pageable pageable);

    Optional<Page<Animal>> findAllByAnimalType(String animalType, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM Animal WHERE animal_group=:animalGroup")
    Optional<List<Animal>> findAllByTerritoryGroup(@Param("animalGroup") String animalGroup);

}
