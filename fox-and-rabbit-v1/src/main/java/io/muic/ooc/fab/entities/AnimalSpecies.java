package io.muic.ooc.fab.entities;

import java.awt.*;

public enum AnimalSpecies {

    RABBIT(0.1, Rabbit.class, Color.CYAN, 9),
    FOX(0.03, Fox.class, Color.ORANGE, 15),
    TIGER(0.01, Tiger.class, Color.BLACK, 60),
    HUNTER(0.0009, Hunter.class, Color.RED, 0);

    private double breedingProbability;

    private Class animalClass;

    private Color color;

    private int foodValue;

    AnimalSpecies(double breedingProbability, Class animalClass, Color color, int foodValue) {
        this.breedingProbability = breedingProbability;
        this.animalClass = animalClass;
        this.color = color;
        // adding the food value for each animals for solving dependence problem //
        this.foodValue = foodValue;
    }

    public double getBreedingProbability() {
        return breedingProbability;
    }

    public Class getAnimalClass() {
        return animalClass;
    }

    public Color getColor() {
        return color;
    }

    public int getFoodValue() {
        return foodValue;
    }
}

