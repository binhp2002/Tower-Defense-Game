package com.example.towerdefence.tests;

import com.example.towerdefence.objects.enemy.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

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
        assertThat(enemy.getRelativeLocation(), instanceOf(int[].class));
    }

    /**
     * verify that location of enemy is changed successfully
     */
    @Test
    public void testSetLocation() {
        assertEquals(enemy.setRelativeLocation(new int[]{1, 2}), 0);
        assertArrayEquals(enemy.getRelativeLocation(), new int[]{1, 2});
    }

    /**
     * check that getDamage works without error
     */
    @Test
    public void testGetDamage() {
        assertThat(enemy.getDamage(), instanceOf(Integer.class));
    }

    /**
     * check that enemy moves horizontal to the correct position
     */
    @Test
    public void testMoveHorizontalNormal() {
        enemy.setRelativeLocation(new int[]{5, 5});
        assertArrayEquals(enemy.getRelativeLocation(), new int[]{5, 5});

        //check that error code is 0
        assertEquals(enemy.moveHorizontal(4), 0);
        //check that the enemy is in the correct new position
        assertArrayEquals(enemy.getRelativeLocation(), new int[]{1, 5});
    }

    /**
     * check that enemy does not move horizontal and gives -1 error code when
     * moving to negative x coordinate
     */
    @Test
    public void testMoveHorizontalNegative() {
        enemy.setRelativeLocation(new int[]{5, 5});
        assertArrayEquals(enemy.getRelativeLocation(), new int[]{5, 5});

        //check that -1 error code returned
        assertEquals(enemy.moveHorizontal(6), -1);
        //check that enemy did not move
        assertArrayEquals(enemy.getRelativeLocation(), new int[]{5, 5});
    }

    /**
     * check that enemy moves horizontal to the correct position when x coord is 0 (edge case)
     */
    @Test
    public void testMoveHorizontalZero() {
        enemy.setRelativeLocation(new int[]{5, 5});
        assertArrayEquals(enemy.getRelativeLocation(), new int[]{5, 5});

        //check that error code is 0
        assertEquals(enemy.moveHorizontal(5), 0);
        //check that the enemy is in the correct new position
        assertArrayEquals(enemy.getRelativeLocation(), new int[]{0, 5});
    }
}
