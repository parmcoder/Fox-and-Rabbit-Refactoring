package io.muic.ooc.fab.entities;

import java.awt.*;

public enum AnimalSpecies {

        RABBIT(0.08, Rabbit.class, Color.ORANGE, 9),
        FOX(0.02, Fox.class, Color.BLUE, 15),
        TIGER(0.02, Tiger.class, Color.RED, 40),
        HUNTER(0.001, Hunter.class, Color.CYAN,0);

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

        AnimalSpecies(double breedingProbability){
            this.breedingProbability = breedingProbability;
        }

        public double getBreedingProbability(){
            return breedingProbability;
        }

        public Class getAnimalClass(){
            return animalClass;
        }

        public Color getColor(){
            return color;
        }

        public int getFoodValue(){
            return foodValue;
        }
}

