package com.example.towerdefence.tests;

import com.example.towerdefence.objects.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProjectileTest {

    private Projectile normalProjectile;

    @Before
    public void setUp() {
        normalProjectile = new NormalProjectile();
    }

    /**
     * check that damage of normal projectile is 10
     */
    @Test
    public void normalProjectileDamage() {
        assertEquals(normalProjectile.getDamage(), 10);
    }

}
