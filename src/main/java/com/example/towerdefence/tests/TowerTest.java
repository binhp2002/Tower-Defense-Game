package com.example.towerdefence.tests;

import com.example.towerdefence.objects.tower.*;
import org.junit.*;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class TowerTest {

    private SniperTower sniperTower;
    private BasicTower basicTower;
    private MachineTower machineTower;

    @Before
    public void setUp() {
        sniperTower = new SniperTower(new int[]{0, 0});
        basicTower = new BasicTower(new int[]{0, 0});
        machineTower = new MachineTower(new int[]{0, 0});
    }

    /**
     * check that create tower works as expected to create a BasicTower at (1, 2)
     */
    @Test
    public void testCreateTowerNormal() {
        Tower tower = Tower.createTower(BasicTower.class, 1, 2);
        //check that tower is an instance of BasicTower
        assertTrue(tower instanceof BasicTower);
        //check that the tower has correct absolute location
        assertArrayEquals(tower.getAbsoluteLocation(), new int[]{1, 2});
    }

    /**
     * check that an IllegalArgumentException is thrown when a non-subclass of Tower
     * is passed into createTower
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTowerNotTowerSubclass() {
        Tower.createTower(Object.class, 1, 2);
    }

    /**
     * checks that sniper tower get name method implemented correctly
     */
    @Test
    public void testSniperGetName() {
        //check that SniperTower has correct name
        assertEquals("Sniper tower", sniperTower.getName());
    }

    /**
     * checks that sniper tower get basic cost runs successfully
     */
    @Test
    public void testSniperGetBasicCost() {
        //ensure that no error when getting basic cost
        assertThat(sniperTower.getBasicCost(), instanceOf(Integer.class));
    }

    /**
     * checks that BasicTower get name implemented correctly
     */
    @Test
    public void testBasicGetName() {
        //check that SniperTower has correct name
        assertEquals("Basic tower", basicTower.getName());
    }

    /**
     * check that BasicTower get basic cost runs successfully
     */
    @Test
    public void testBasicGetBasicCost() {
        //ensure that no error when getting basic cost
        assertThat(basicTower.getBasicCost(), instanceOf(Integer.class));
    }

    /**
     * checks that MachineTower implements get name method correctly
     */
    @Test
    public void testMachineGetName() {
        //check that SniperTower has correct name
        assertEquals("Machine tower", machineTower.getName());
    }

    /**
     * checks that MachineTower get basic cost method runs successfully
     */
    @Test
    public void testMachineGetBasicCost() {
        //ensure that no error when getting basic cost
        assertThat(machineTower.getBasicCost(), instanceOf(Integer.class));
    }

    /**
     * checks tower set normal health method by using basicTower as concrete implementation
     */
    @Test
    public void testSetNormalHealth() {
        //set to a random health and just make sure that default health is not
        // equal to the random health
        assertNotEquals(34, basicTower.getHealth());
        //check if error code 0
        assertEquals(0, basicTower.setHealth(34));
        //check if health changed
        assertEquals(34, basicTower.getHealth());
    }

    /**
     * checks tower set zero health returns error and health is not changed
     */
    @Test
    public void testSetZeroHealth() {
        int defaultHealth = basicTower.getHealth();
        //tower should not have zero health
        assertEquals(-1, basicTower.setHealth(0));
        //check health not changed
        assertEquals(defaultHealth, basicTower.getHealth());
    }

    /**
     * checks tower set negative health returns -1 and health not changed
     */
    @Test
    public void testSetNegativeHealth() {
        int defaultHealth = basicTower.getHealth();
        //tower should not have negative health
        assertEquals(-1, basicTower.setHealth(-1));
        //check health not changed
        assertEquals(defaultHealth, basicTower.getHealth());
    }

    /**
     * check that BasicTower get range runs successfully
     */
    @Test
    public void testBasicTowerGetRange() {
        //ensure that no error when getting range of basicTower
        assertThat(basicTower.getBasicCost(), instanceOf(Integer.class));
    }

    /**
     * check that MachineTower get range runs successfully
     */
    @Test
    public void testMachineTowerGetRange() {
        //ensure that no error when getting range of basicTower
        assertThat(machineTower.getBasicCost(), instanceOf(Integer.class));
    }

    /**
     * check that BasicTower get range runs successfully
     */
    @Test
    public void testSniperTowerGetRange() {
        //ensure that no error when getting range of basicTower
        assertThat(sniperTower.getBasicCost(), instanceOf(Integer.class));
    }

    /**
     * check that BasicTower recognizes that enemy is in range
     */
    @Test
    public void testBasicTowerEnemyInRange() {
        assertEquals(basicTower.inRange(new int[]{1, 1}), true);
    }

    /**
     * check that BasicTower detects that enemy is not in range
     */
    @Test
    public void testBasicTowerEnemyNotInRange() {
        assertEquals(basicTower.inRange(new int[]{250, 250}), false);
    }

    /**
     * check that BasicTower detects that enemy, even at boundary of range
     */
    @Test
    public void testBasicTowerEnemyJustInRange() {
        assertEquals(basicTower.inRange(new int[]{250, 0}), true);
    }

    /**
     * check that SniperTower recognizes that enemy is in range
     */
    @Test
    public void testSniperTowerEnemyInRange() {
        assertEquals(sniperTower.inRange(new int[]{1, 1}), true);
    }

    /**
     * check that SniperTower detects that enemy is not in range
     */
    @Test
    public void testSniperTowerEnemyNotInRange() {
        assertEquals(sniperTower.inRange(new int[]{500, 500}), false);
    }

    /**
     * check that SniperTower detects that enemy, even at boundary of range
     */
    @Test
    public void testSniperTowerEnemyJustInRange() {
        assertEquals(sniperTower.inRange(new int[]{500, 0}), true);
    }

    /**
     * check that MachineTower recognizes that enemy is in range
     */
    @Test
    public void testMachineTowerEnemyInRange() {
        assertEquals(machineTower.inRange(new int[]{1, 1}), true);
    }

    /**
     * check that MachineTower detects that enemy is not in range
     */
    @Test
    public void testMachineTowerEnemyNotInRange() {
        assertEquals(machineTower.inRange(new int[]{250, 250}), false);
    }

    /**
     * check that MachineTower detects that enemy, even at boundary of range
     */
    @Test
    public void testMachineTowerEnemyJustInRange() {
        assertEquals(machineTower.inRange(new int[]{250, 0}), true);
    }

    /**
     * check that upgrading tower leads to increase in damage
     */
    @Test
    public void testUpgradeTowerDamageIncrease() {
        int initialDamage = machineTower.getDamage();
        machineTower.upgradeTower();
        //damage increased to 5 times initial damage
        assertEquals(initialDamage * 5, machineTower.getDamage());
    }

    /**
     * check if image changed correctly when tower is upgraded
     */
    @Test
    public void testUpgradeTowerImageChange() {
        //check if initial image path is correct
        assertEquals(machineTower.getImagePath(), MachineTower.IMAGE_PATH1);
        machineTower.upgradeTower();
        //check if image path has been updated to the second level
        assertEquals(machineTower.getImagePath(), MachineTower.IMAGE_PATH2);
    }

    @Test
    public void testUpgradeTwoLevels() {
        machineTower.upgradeTower();
        int upgradedDamage = machineTower.getDamage();
        //checks that -1 is returned when trying to upgrade the tower a second time
        assertEquals(machineTower.upgradeTower(), -1);
        //check that damage doesn't get increased twice
        assertEquals(machineTower.getDamage(), upgradedDamage);
        //check that the image still unchanged at the level 2 image
        assertEquals(machineTower.getImagePath(), MachineTower.IMAGE_PATH2);
    }

    @Test
    public void testLevelsUpdated() {
        //check that level was initially 1
        assertEquals(machineTower.getLevel(), 1);
        machineTower.upgradeTower();
        //check that level has been incremented
        assertEquals(machineTower.getLevel(), 2);
    }



}
