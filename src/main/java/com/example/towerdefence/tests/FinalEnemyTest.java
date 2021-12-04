package com.example.towerdefence.tests;

import com.example.towerdefence.objects.enemy.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

public class FinalEnemyTest {

    private Enemy finalEnemy;

    @Before
    public void setUp() {
        finalEnemy = new BasicEnemy();
    }

    /**
     * check that getLocation works without error
     */
    @Test
    public void testGetLocation() {
        assertThat(finalEnemy.getRelativeLocation(), instanceOf(int[].class));
    }

    /**
     * verify that location of enemy is changed successfully
     */
    @Test
    public void testSetLocation() {
        assertEquals(finalEnemy.setRelativeLocation(new int[]{1, 2}), 0);
        assertArrayEquals(finalEnemy.getRelativeLocation(), new int[]{1, 2});
    }

    /**
     * check that getDamage works without error
     */
    @Test
    public void testGetDamage() {
        assertThat(finalEnemy.getDamage(), instanceOf(Integer.class));
    }

    /**
     * check that enemy moves horizontal to the correct position
     */
    @Test
    public void testMoveHorizontalNormal() {
        finalEnemy.setRelativeLocation(new int[]{5, 5});
        assertArrayEquals(finalEnemy.getRelativeLocation(), new int[]{5, 5});

        //check that error code is 0
        assertEquals(finalEnemy.moveHorizontal(4), 0);
        //check that the enemy is in the correct new position
        assertArrayEquals(finalEnemy.getRelativeLocation(), new int[]{1, 5});
    }

    /**
     * check that enemy does not move horizontal and gives -1 error code when
     * moving to negative x coordinate
     */
    @Test
    public void testMoveHorizontalNegative() {
        finalEnemy.setRelativeLocation(new int[]{5, 5});
        assertArrayEquals(finalEnemy.getRelativeLocation(), new int[]{5, 5});

        //check that -1 error code returned
        assertEquals(finalEnemy.moveHorizontal(6), -1);
        //check that enemy did not move
        assertArrayEquals(finalEnemy.getRelativeLocation(), new int[]{5, 5});
    }

    /**
     * check that enemy moves horizontal to the correct position when x coord is 0 (edge case)
     */
    @Test
    public void testMoveHorizontalZero() {
        finalEnemy.setRelativeLocation(new int[]{5, 5});
        assertArrayEquals(finalEnemy.getRelativeLocation(), new int[]{5, 5});

        //check that error code is 0
        assertEquals(finalEnemy.moveHorizontal(5), 0);
        //check that the enemy is in the correct new position
        assertArrayEquals(finalEnemy.getRelativeLocation(), new int[]{0, 5});
    }
}
