package com.every.wildlife.animal.config;

public enum AnimalType {

    AGNATHA ("Agnatha"),
    CHRONDRICHTYES("Chrondrichtyes"),
    OSTEICHTHYES("Osteichthyes"),
    AMPHIBIA("Amphibia"),
    REPTILIA("Reptilia"),
    AVES("Aves"),
    MAMMALIA("Mammalia");

    String animalType;

    AnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalType() {
        return animalType;
    }
}
