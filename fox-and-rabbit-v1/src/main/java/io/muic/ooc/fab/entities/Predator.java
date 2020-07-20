package io.muic.ooc.fab.entities;

import io.muic.ooc.fab.Utilities.Location;

public interface Predator {
    void incrementHunger();

    Location findFood();
}
