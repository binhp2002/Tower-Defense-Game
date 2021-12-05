package com.example.towerdefence.tests;
import static org.junit.Assert.*;

import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.enemy.*;
import com.example.towerdefence.objects.tower.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TowerRowTest {
    private TowerRow towerRow;

    @Before
    public void setUp() {
        towerRow = new TowerRow(5, 10);
    }

    @Test
    public void testNumRowsTowerRow() {
        //check that TowerRow has been properly initialized with correct num of rows
        assertEquals(5, towerRow.getNumRows());
    }

    @Test
    public void testNumColumnsTowerRow() {
        //check that TowerRow has been properly initialized with correct num of columns
        assertEquals(10, towerRow.getNumColumns());
    }

    @Test
    public void testAddTowerNormal() {
        Tower tower = new BasicTower(new int[]{0, 0});
        assertNull(towerRow.getTower(4, 3));
        towerRow.insertTower(4, 3, tower);
        //check that tower is now in row = 4, column = 3
        assertEquals(towerRow.getTower(4, 3), tower);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTowerRowTooLarge() {
        Tower tower = new BasicTower(new int[]{0, 0});
        //should throw IllegalArgumentException
        towerRow.insertTower(5, 3, tower);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTowerRowNegative() {
        Tower tower = new BasicTower(new int[]{0, 0});
        //should throw IllegalArgumentException
        towerRow.insertTower(-1, 3, tower);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTowerColumnTooLarge() {
        Tower tower = new BasicTower(new int[]{0, 0});
        //should throw IllegalArgumentException
        towerRow.insertTower(-1, 10, tower);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTowerColumnNegative() {
        Tower tower = new BasicTower(new int[]{0, 0});
        //should throw IllegalArgumentException
        towerRow.insertTower(4, -3, tower);
    }

    /**
     * check if enemy health gets appropriately decreased based on damage by the tower
     */
    @Test
    public void testDamageEnemiesNoKill() {
        //place the enemyWave origin a distance from location
        int distance = 10;
        //add enemy to origin
        EnemyWave enemyWave = new EnemyWave(new int[]{0, distance});
        Enemy tankEnemy = new TankEnemy(0, 0);
        enemyWave.addEnemy(tankEnemy);
        //add tower to origin
        Tower tower = new BasicTower(new int[]{0, 0});
        towerRow.insertTower(0, 0, tower);

        //one shot shouldn't be significant enough to kill an enemy
        assertTrue(tankEnemy.getHealth() > tower.getDamage());
        //enemy should not be killed so return 0 size list
        assertEquals(towerRow.damageEnemies(enemyWave, System.nanoTime()).size(), 0);
        //check that range is greater than distance
        assertTrue(tower.getRange() >= distance);
        //check that health has been reduced by one round worth of damage
        assertEquals(tankEnemy.getHealth(), tankEnemy.getFullHealth() - tower.getDamage());
    }

    /**
     * check that enemy can get hit by 2 towers and killed
     * (enemy is at the limit of both towers ranges)
     */
    @Test(timeout = 3000)
    public void testDamageEnemiesKill() {
        Tower tower1 = new BasicTower(new int[]{0, 0});
        towerRow.insertTower(0, 0, tower1);

        int distance = tower1.getRange();

        //add enemy to origin
        EnemyWave enemyWave = new EnemyWave(new int[]{0, distance});
        Enemy tankEnemy = new TankEnemy(0, 0);
        enemyWave.addEnemy(tankEnemy);

        //this tower is below the enemy
        Tower tower2 = new BasicTower(new int[]{0, distance * 2});
        //insert to another row and column so it doesn't override the original tower
        towerRow.insertTower(0, 1, tower2);

        List<Enemy> killedEnemies = null;
        while (killedEnemies == null || killedEnemies.size() == 0) {
            //enemy should not be killed so return 0 size list
            int origHealth = tankEnemy.getHealth();
            killedEnemies = towerRow.damageEnemies(enemyWave, System.nanoTime());
            assertTrue(tankEnemy.getHealth() == 0 || tankEnemy.getHealth()
                    == origHealth - tower1.getDamage() * 2 || tankEnemy.getHealth() == origHealth);
        }
        //verify that the enemy that was killed is tankEnemy and it is returned appropriately
        assertEquals(killedEnemies.get(0), tankEnemy);
    }


}
