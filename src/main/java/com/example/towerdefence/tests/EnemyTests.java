package com.example.towerdefence.tests;

import com.example.towerdefence.objects.enemy.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class EnemyTests {

    private Enemy enemy;

    @Before
    public void setUp() {
        enemy = new BasicEnemy();
    }

    /**
     * check that getLocation works without error
     */
    @Test
    public void testGetLocation() {
        assertThat(enemy.getLocation(), instanceOf(int[].class));
    }

    /**
     * verify that location of enemy is changed successfully
     */
    @Test
    public void testSetLocation() {
        assertEquals(enemy.setLocation(new int[]{1, 2}), 0);
        assertArrayEquals(enemy.getLocation(), new int[]{1, 2});
    }

    /**
     * check that getDamage works without error
     */
    @Test
    public void testGetDamage() {
        assertThat(enemy.getDamage(), instanceOf(Integer.class));
    }

}
