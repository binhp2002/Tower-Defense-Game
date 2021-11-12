package com.example.towerdefence.tests;
import static org.junit.Assert.*;

import com.example.towerdefence.objects.tower.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;

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

}
