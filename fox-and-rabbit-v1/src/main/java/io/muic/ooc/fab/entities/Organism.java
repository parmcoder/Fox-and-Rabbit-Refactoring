package io.muic.ooc.fab.entities;

import io.muic.ooc.fab.Location;

import java.util.List;

public interface Organism {
    void run(List<Organism> newLives);

    boolean isAlive();

    void setDead();

    Location getLocation();

    void setLocation(Location newLocation);

    void incrementAge();

    void giveBirth(List<Organism> newLives);

    int breed();

    boolean canBreed();
}
