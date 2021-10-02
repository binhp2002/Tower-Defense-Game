package com.example.towerdefence.tests;

import com.example.towerdefence.objects.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class PlayerTest {

    Player player;

    /**
     * set up to create player object
     */
    @Before
    public void setUp() {
        player = new Player();
    }

    /**
     * test expected name which is non-empty, non-null, and has non-whitespace characters
     */
    @Test
    public void testSetExpectedName(){
        //return 0 error code
        assertEquals(player.setName("test"), 0);
        assertEquals(player.getName(), "test");
    }

    /**
     * checks expected name with whitespaces
     */
    @Test
    public void testSetExpectedNameWithWhitespace(){
        assertEquals(player.setName("test one"), 0);
        assertEquals(player.getName(), "test one");
    }

    /**
     * test error code (-1) for null name and checks that name was not changed
     */
    @Test
    public void testSetNullName() {
        player.setName("test");
        assertEquals(player.setName(null), -1);
        assertEquals(player.getName(), "test");
    }

    /**
     * tests error code (-1) when trying to set empty name and checks that name was
     * not changed
     */
    @Test
    public void testSetEmptyName() {
        assertEquals(player.setName(""), -1);
        assertNull(player.getName());
    }

    /**
     * tests error code (-1) when trying to set whitespace only name and checks that
     * name was not changed
     */
    @Test
    public void testSetWhiteSpaceName() {
        assertEquals(player.setName("    "), -1);
        assertNull(player.getName());
    }

    /**
     * tests if getName() returns null if name was not set
     */
    @Test
    public void testGetInitialName() {
        assertNull(player.getName());
    }

    /**
     * check if setMoney works on positive amount of money
     */
    @Test
    public void testSetPositiveMoney() {
        //return 0 as error code
        assertEquals(player.setMoney(500), 0);
        //return 500 as amount of money
        assertEquals(player.getMoney(), 500);
    }

    /**
     * check if setMoney works on 0 money
     */
    @Test
    public void testSetZeroMoney() {
        //return 0 as error code
        assertEquals(player.setMoney(0), 0);
        //return 0 as amount of money
        assertEquals(player.getMoney(), 0);
    }

    /**
     * check if setMoney returns -1 on negative money and money not changed
     */
    @Test
    public void testSetNegativeMoney() {
        //set player to have 500
        player.setMoney(500);
        assertEquals(player.getMoney(), 500);
        //return -1 as error code
        assertEquals(player.setMoney(-2), -1);
        //money is unchanged, still 500
        assertEquals(player.getMoney(), 500);
    }

    /**
     * check if initial money is 0
     */
    @Test
    public void testGetInitialMoney() {
        //initially player has 0 money unless otherwise set
        assertEquals(player.getMoney(), 0);
    }

    /**
     * testing set difficulty
     */
    @Test
    public void testSetDifficulty() {
        //check error code 0
        assertEquals(player.setDifficulty(5), 0);
        //check if difficulty changed to 5
        assertEquals(player.getDifficulty(), 5);
    }

    /**
     * check if initial difficulty is 0 unless otherwise set
     */
    @Test
    public void testGetInitialDifficulty() {
        assertEquals(player.getDifficulty(), 0);
    }
}