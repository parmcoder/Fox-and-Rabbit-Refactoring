package io.muic.ooc.fab.entities;

import io.muic.ooc.fab.Utilities.Location;

public class Hunter extends Animal{
    @Override
    public Location moveToNewLocation() {
        return null;
    }

    @Override
    protected int getBreedingAge() {
        return 0;
    }

    @Override
    protected int getMaxLiterSize() {
        return 0;
    }

    @Override
    protected double getBreedingProbability() {
        return 0;
    }

    @Override
    protected int getMaxAge() {
        return 0;
    }
}
