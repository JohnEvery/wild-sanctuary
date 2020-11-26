package com.every.wildlife.animal.config;

public enum AnimalGroup {

    OVERPOPULATION("Overpopulation"),
    ABOVE_NORMAL("Above normal"),
    NORMAL("Normal"),
    BELOW_NORMAL("Below normal"),
    ENDANGERED_TYPE("Endangered type");

    String animalGroup;

    AnimalGroup(String animalGroup) {
        this.animalGroup = animalGroup;
    }

    public String getAnimalGroup() {
        return animalGroup;
    }
}
