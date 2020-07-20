package io.muic.ooc.fab.entities;

import io.muic.ooc.fab.Field;
import io.muic.ooc.fab.Location;

import java.util.List;
import java.util.Random;

public abstract class Animal implements Organism {
    // Characteristics shared by all animals (class variables).

    // Random generator
    protected static final Random RANDOM = new Random();

    // Encapsulation
    // Whether the animal is alive or not.
    private boolean alive = true;

    // The animal's position.
    private Location location;
    // The field occupied.
    protected Field field;
    // Individual characteristics (instance fields).
    // The animal's age.
    private int age = 0;

    /**
     * Create a new animal. A rabbit may be created with age zero (a new born)
     * or with a random age.
     *
     * @param randomAge If true, the animal will have a random age.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public void initialize(boolean randomAge, Field field, Location location) {
        this.field = field;
        setLocation(location);
        if (randomAge) {
            age = RANDOM.nextInt(getMaxAge());
        }
    }

    public abstract Location moveToNewLocation();

    @Override
    public void act(List<Organism> newAnimals) {
        incrementAge();
        if (isAlive()) {
            giveBirth(newAnimals);
            // Try to move into a free location.
            Location newLocation = moveToNewLocation();

            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * This is what the animal does most of the time - it runs around. Sometimes
     * it will breed or die of old age.
     *
     * @param newAnimals A list to return newly born rabbits.
     */
    @Override
    public void run(List<Organism> newAnimals) {
        incrementAge();
        if (alive) {
            giveBirth(newAnimals);
            // Try to move into a free location.
            Location newLocation = field.freeAdjacentLocation(location);
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Check whether the animal is alive or not.
     *
     * @return true if the animal is still alive.
     */
    @Override
    public boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive. It is removed from the
     * field.
     */
    @Override
    public void setDead() {
        alive = false;
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     *
     * @return The animal's location.
     */
    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * Place the animal at the new location in the given field.
     *
     * @param newLocation The animal's new location.
     */
    @Override
    public void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Increase the age. This could result in the animal's death.
     */
    @Override
    public void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    protected abstract int getMaxAge();

    /**
     * Check whether or not this animal is to give birth at this step. New
     * births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born animals.
     */
    @Override
    public void giveBirth(List<Organism> newAnimals) {
        // New animals are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            /*
            ? Need a factory for abstract class
             */
            Animal young = breedOne(false, field, loc);
            newAnimals.add(young);
        }
    }

    private Animal breedOne(boolean randomAge, Field field, Location location) {
        return AnimalFactory.createAnimal(getClass(), field, location);
    }


    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    @Override
    public int breed() {
        int births = 0;
        if (canBreed() && RANDOM.nextDouble() <= getBreedingProbability()) {
            births = RANDOM.nextInt(getMaxLiterSize()) + 1;
        }
        return births;
    }

    protected abstract int getMaxLiterSize();

    protected abstract double getBreedingProbability();

    /**
     * A rabbit can breed if it has reached the breeding age.
     *
     * @return true if the rabbit can breed, false otherwise.
     */
    @Override
    public boolean canBreed() {
        return age >= getBreedingAge();
    }

    protected abstract int getBreedingAge();
}
