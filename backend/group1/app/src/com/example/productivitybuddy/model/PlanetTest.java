package com.example.productivitybuddy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void testPlanetConstructor() {
        Planet planet = new Planet("Mars", 5);

        assertEquals("Mars", planet.getName());
        assertEquals(5, planet.getUnlockHours());
    }

    @Test
    void testSettersAndGetters() {
        Planet planet = new Planet();

        planet.setName("Jupiter");
        planet.setUnlockHours(15);

        assertEquals("Jupiter", planet.getName());
        assertEquals(15, planet.getUnlockHours());
    }

    @Test
    void testIdInitiallyNull() {
        Planet planet = new Planet("Earth", 0);

        // Since JPA generates ID, it should be null before saving
        assertNull(planet.getId());
    }
}