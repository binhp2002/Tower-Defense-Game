package com.example.towerdefence.tests;

import com.example.towerdefence.objects.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TowerTest {

    SniperTower sniperTower;
    BasicTower basicTower;
    MachineTower machineTower;

    @Before
    public void setUp() {
        sniperTower = new SniperTower();
        basicTower = new BasicTower();
        machineTower = new MachineTower();
    }

    /**
     * checks that SniperTower shoot method returns a SniperProjectile object
     */
    @Test
    public void testSniperTowerShoot() {
        //check that the correct projetile was fired
        assertTrue(sniperTower.shoot() instanceof SniperProjectile);
    }

    /**
     * checks that sniper tower get name method implemented correctly
     */
    @Test
    public void testSniperGetName() {
        //check that SniperTower has correct name
        assertEquals( "Sniper Tower", sniperTower.getName());
    }

    /**
     * checks that sniper tower get basic cost runs successfully
     */
    @Test
    public void testSniperGetBasicCost() {
        //ensure that no error when getting basic cost
        sniperTower.getBasicCost();
    }

    /**
     * checks that BasicTower shoots a NormalProjectile
     */
    @Test
    public void testBasicTowerShoot() {
        assertTrue(basicTower.shoot() instanceof NormalProjectile);
    }

    /**
     * checks that BasicTower get name implemented correctly
     */
    @Test
    public void testBasicGetName() {
        //check that SniperTower has correct name
        assertEquals("Basic Tower", basicTower.getName());
    }

    /**
     * check that BasicTower get basic cost runs successfully
     */
    @Test
    public void testBasicGetBasicCost() {
        //ensure that no error when getting basic cost
        basicTower.getBasicCost();
    }

    /**
     * checks MachineTower shoots a SmallProjectile
     */
    @Test
    public void testMachineTowerShoot() {
        assertTrue(machineTower.shoot() instanceof SmallProjectile);
    }

    /**
     * checks that MachineTower implements get name method correctly
     */
    @Test
    public void testMachineGetName() {
        //check that SniperTower has correct name
        assertEquals("Machine Tower", machineTower.getName());
    }

    /**
     * checks that MachineTower get basic cost method runs successfully
     */
    @Test
    public void testMachineGetBasicCost() {
        //ensure that no error when getting basic cost
        machineTower.getBasicCost();
    }

    /**
     * checks Tower set normal health method by using basicTower as concrete implementation
     */
    @Test
    public void testSetNormalHealth() {
        //set to a random health and just make sure that default health is not equal to the random health
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

}
