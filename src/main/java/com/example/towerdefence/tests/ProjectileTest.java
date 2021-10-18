package com.example.towerdefence.tests;

import com.example.towerdefence.objects.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProjectileTest {

    private Projectile normalProjectile;

    @Before
    public void setUp() {
        normalProjectile = new NormalProjectile();
    }

    /**
     * check that the damage of normal projectile is 10
     */
    @Test
    public void normalProjectileDamage() {
        assertEquals(normalProjectile.getDamage(), 10);
    }

}
