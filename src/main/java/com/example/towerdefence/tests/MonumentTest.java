package com.example.towerdefence.tests;

import com.example.towerdefence.objects.Monument;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MonumentTest {

    private Monument monument;

    @Before
    public void setUp() {
        monument = new Monument();
    }

    /**
     * checking set health of monument to normal positive value
     */
    @Test
    public void testSetNormalHealth() {
        //error code is 0
        assertEquals(monument.setHealth(500), 0);
        //check if health changed to 500
        assertEquals(monument.getHealth(), 500);
    }

    /**
     * check if initial health of monument is -1
     */
    @Test
    public void testGetInitialHealth() {
        //check if initial health is -1
        assertEquals(monument.getHealth(), -1);
    }
}