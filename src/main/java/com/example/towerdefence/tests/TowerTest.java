package com.example.towerdefence.tests;

import com.example.towerdefence.objects.projectile.*;
import com.example.towerdefence.objects.tower.*;
import org.junit.*;

import java.io.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class TowerTest {

    private SniperTower sniperTower;
    private BasicTower basicTower;
    private MachineTower machineTower;

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
        assertEquals("Sniper tower", sniperTower.getName());
    }

    /**
     * checks that sniper tower get basic cost runs successfully
     */
    @Test
    public void testSniperGetBasicCost() {
        //ensure that no error when getting basic cost
        assertThat(sniperTower.getBasicCost(), instanceOf(Integer.class)) ;
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

    @Test
    public void testGetImagePathBasic() {
        File image = new File(basicTower.getImagePath());
        System.out.println(basicTower.getImagePath());
        assertTrue(image.exists());
    }

}
