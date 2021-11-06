package com.example.towerdefence.tests;

import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.tower.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class PlayerTest {

    private Player player;

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
    public void testSetExpectedName() {
        //return 0 error code
        assertEquals(player.setName("test"), 0);
        assertEquals(player.getName(), "test");
    }

    /**
     * checks expected name with whitespaces
     */
    @Test
    public void testSetExpectedNameWithWhitespace() {
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

    /**
     * test if cost factor for difficulty level difficult is set as two times
     */
    @Test
    public void testGetCostFactorDifficult() {
        assertEquals(player.setDifficulty(3), 0);
        assertEquals(player.getDifficulty(), 3);
        //check that cost factor was correctly changed
        assertEquals(player.getCostFactor(), 2, 0.01);
    }

    /**
     * test if cost factor for difficulty level medium is set as two times
     */
    @Test
    public void testGetCostFactorMedium() {
        assertEquals(player.setDifficulty(2), 0);
        assertEquals(player.getDifficulty(), 2);
        //check that cost factor was correctly changed
        assertEquals(player.getCostFactor(), 1.5, 0.01);
    }

    /**
     * test if cost factor for difficulty level easy is set as two times
     */
    @Test
    public void testGetCostFactorEasy() {
        assertEquals(player.setDifficulty(1), 0);
        assertEquals(player.getDifficulty(), 1);
        //check that cost factor was correctly changed
        assertEquals(player.getCostFactor(), 1, 0.01);
    }

    /**
     * test if getPlayerCost uses the basic cost of the tower as the cost for the player
     */
    @Test
    public void testGetPlayerCostEasy() {
        Tower basicTower = new BasicTower(new int[]{0, 0});
        assertEquals(player.setDifficulty(1), 0);
        assertEquals(player.getDifficulty(), 1);
        //check cost factor correctly applied
        assertEquals(player.getPlayerCost(basicTower), basicTower.getBasicCost());
    }

    /**
     * test if getPlayerCost uses the basic cost of tower * 1.5 as the cost for the player
     */
    @Test
    public void testGetPlayerCostMedium() {
        Tower basicTower = new BasicTower(new int[]{0, 0});
        assertEquals(player.setDifficulty(2), 0);
        assertEquals(player.getDifficulty(), 2);
        //check cost factor correctly applied
        assertEquals(player.getPlayerCost(basicTower), (int) (basicTower.getBasicCost() * 1.5));
    }

    @Test
    public void testGetPlayerCostHard() {
        Tower sniperTower = new SniperTower(new int[]{0, 0});
        assertEquals(player.setDifficulty(3), 0);
        assertEquals(player.getDifficulty(), 3);
        //check cost factor corretly applied
        assertEquals(player.getPlayerCost(sniperTower), (int) (sniperTower.getBasicCost() * 2));
    }

    @Test
    public void testGetPlayerCostHardTowerClass() {
        assertEquals(player.setDifficulty(3), 0);
        assertEquals(player.getDifficulty(), 3);
        //check cost factor corretly applied
        assertEquals(player.getPlayerCost(SniperTower.class),
                (int) (SniperTower.BASIC_COST * 2));
    }

    @Test
    public void testSetCurrSelectedNull() {
        //change from the default null value
        assertEquals(player.setCurrSelected(BasicTower.class), 0);
        //check changed
        assertEquals(player.getCurrSelected(), BasicTower.class);
        //change to null and check if changed to nukk
        assertEquals(player.setCurrSelected(null), 0);
        assertNull(player.getCurrSelected());
    }

    @Test
    public void testSetCurrSelectedNonTower() {
        //verify that -1 error code returns if setting curr selected to non-tower class
        assertEquals(player.setCurrSelected(Object.class), -1);
        //check that curr selected was not changed
        assertNull(player.getCurrSelected());
    }

    @Test
    public void testSetCurrSelectedTower() {
        //verify that 0 error code returned
        assertEquals(player.setCurrSelected(SniperTower.class), 0);
        //ensure that curr selected for player is SniperTower
        assertEquals(player.getCurrSelected(), SniperTower.class);
    }


}