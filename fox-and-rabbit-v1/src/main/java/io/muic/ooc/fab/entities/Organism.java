package io.muic.ooc.fab.entities;

import io.muic.ooc.fab.Utilities.Field;
import io.muic.ooc.fab.Utilities.Location;

import java.util.List;

public interface Organism {

    /**
     * Create a new animal. An animal may be created with age zero (a new born)
     * or with a random age.
     *
     * @param randomAge If true, the animal will have a random age.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    void initialize(boolean randomAge, Field field, Location location);


    void act(List<Organism> newAnimals);

    /**
     * This is what the animal does most of the time - it runs around. Sometimes
     * it will breed or die of old age.
     *
     * @param newAnimals A list to return newly born rabbits.
     */
    void run(List<Organism> newAnimals);

    /**
     * Check whether the animal is alive or not.
     *
     * @return true if the animal is still alive.
     */
    boolean isAlive();

    /**
     * Indicate that the animal is no longer alive. It is removed from the
     * field.
     */
    void setDead();

    /**
     * Return the animal's location.
     *
     * @return The animal's location.
     */
    Location getLocation();

    /**
     * Place the animal at the new location in the given field.
     *
     * @param newLocation The animal's new location.
     */
    void setLocation(Location newLocation);

    /**
     * Increase the age. This could result in the animal's death.
     */
    void incrementAge();

    /**
     * Check whether or not this animal is to give birth at this step. New
     * births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born animals.
     */
    void giveBirth(List<Organism> newAnimals);

    Animal breedOne(Field field, Location location);

    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    int breed();

    /**
     * A rabbit can breed if it has reached the breeding age.
     *
     * @return true if the rabbit can breed, false otherwise.
     */
    boolean canBreed();
}
