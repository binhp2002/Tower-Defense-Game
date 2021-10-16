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

    @Test
    public void testSniperTowerShoot() {
        //check that the correct projetile was fired
        assertTrue(sniperTower.shoot() instanceof SniperProjectile);
    }

    @Test
    public void testSniperGetName() {
        //check that SniperTower has correct name
        assertEquals( "Sniper Tower", sniperTower.getName());
    }

    @Test
    public void testSniperGetBasicCost() {
        //ensure that no error when getting basic cost
        sniperTower.getBasicCost();
    }

    @Test
    public void testBasicTowerShoot() {
        assertTrue(basicTower.shoot() instanceof NormalProjectile);
    }

    @Test
    public void testBasicGetName() {
        //check that SniperTower has correct name
        assertEquals("Basic Tower", basicTower.getName());
    }

    @Test
    public void testBasicGetBasicCost() {
        //ensure that no error when getting basic cost
        basicTower.getBasicCost();
    }

    @Test
    public void testMachineTowerShoot() {
        assertTrue(machineTower.shoot() instanceof SmallProjectile);
    }

    @Test
    public void testMachineGetName() {
        //check that SniperTower has correct name
        assertEquals("Machine Tower", machineTower.getName());
    }

    @Test
    public void testMachineGetBasicCost() {
        //ensure that no error when getting basic cost
        machineTower.getBasicCost();
    }

    @Test
    public void testSetNormalHealth() {
        //set to a random health and just make sure that default health is not equal to the random health
        assertNotEquals(34, basicTower.getHealth());
        //check if error code 0
        assertEquals(0, basicTower.setHealth(34));
        //check if health changed
        assertEquals(34, basicTower.getHealth());
    }

    @Test
    public void testSetZeroHealth() {
        //tower should not have zero health
        assertEquals(-1, basicTower.setHealth(0));
    }

    @Test
    public void testSetNegativeHealth() {
        //tower should not have negative health
        assertEquals(-1, basicTower.setHealth(-1));
    }

}
