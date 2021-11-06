package com.example.towerdefence.tests;

import com.example.towerdefence.objects.tower.TowerRow;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;

public class TowerRowTest {
    private TowerRow towerRow;

    @Before
    private void setUp() {
        towerRow = new TowerRow(5, 10);
    }

    @Test
    private void testNumRowsTowerRow() {
        //check that TowerRow has been properly initialized with correct num of rows
        assertEquals(5, towerRow.getNumRows());
    }

    @Test
    private void testNumColumnsTowerRow() {
        //check that TowerRow has been properly initialized with correct num of columns
        assertEquals(10, towerRow.getNumRows());
    }

}
