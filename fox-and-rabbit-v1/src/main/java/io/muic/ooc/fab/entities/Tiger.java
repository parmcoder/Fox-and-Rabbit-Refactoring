package io.muic.ooc.fab.entities;

import io.muic.ooc.fab.utilities.Field;
import io.muic.ooc.fab.utilities.Location;

import java.util.Iterator;
import java.util.List;

public class Tiger extends Animal {

    // The tiger's food level, which is increased by eating rabbits and foxes.
    private int foodLevel;

    @Override
    public void initialize(boolean randomAge, Field field, Location location) {
        super.initialize(randomAge, field, location);
        foodLevel = RANDOM.nextInt(AnimalSpecies.FOX.getFoodValue());
    }

    @Override
    public Location moveToNewLocation() {
        Location newLocation = findFood();
        if (newLocation == null) {
            newLocation = field.freeAdjacentLocation(getLocation());
        }
        return newLocation;
    }

    @Override
    protected int getMaxAge() {
        return 175;
    }

    @Override
    protected double getBreedingProbability() {
        return 0.01;
    }

    @Override
    protected int getMaxLiterSize() {
        return 2;
    }

    @Override
    protected int getBreedingAge() {
        return 20;
    }

    public void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    @Override
    public void act(List<Organism> newAnimals) {
        super.act(newAnimals);
        incrementHunger();
    }

    public Location findFood() {
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = AnimalSpecies.RABBIT.getFoodValue();
                    return where;
                }
            }
            if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    foodLevel = AnimalSpecies.FOX.getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }
}
