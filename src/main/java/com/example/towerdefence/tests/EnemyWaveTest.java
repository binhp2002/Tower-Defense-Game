package com.example.towerdefence.tests;

import com.example.towerdefence.objects.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class EnemyWaveTest {

    EnemyWave enemyWave;

    @Before
    public void setUp() {
        enemyWave = new EnemyWave();
        //verify that there are no enemies at the start
        assertEquals(enemyWave.getNumCurrEnemies(), 0);
    }

    @Test
    public void testCreateEnemy() {
        enemyWave.addEnemy(10, 20);
        //check that an enemy has been added
        assertEquals(enemyWave.getNumCurrEnemies(), 1);
        List<int []> enemyLocations = enemyWave.getEnemyLocations();
        //make sure there's only one location
        assertEquals(enemyWave.getEnemyLocations().size(), 1);
        //check that the location is the same (x, y)
        assertArrayEquals(enemyLocations.get(0), new int[]{10, 20});
    }

    @Test
    public void testMoveEnemiesForward() {
        int[][] enemyLocations = new int[][]{{10, 20}, {5, 6}, {7, 10}};
        for (int[] enemyLocation: enemyLocations) {
            //add the enemies to enemyWave with location as enemyLocation
            enemyWave.addEnemy(enemyLocation[0], enemyLocation[1]);
        }
        //check that 3 enemies are inside
        assertEquals(enemyWave.getNumCurrEnemies(), 3);
        
        //no enemies should move past the end
        assertEquals(enemyWave.moveEnemiesForward(5), 0);

        //one enemy moves past the end
        assertEquals(enemyWave.moveEnemiesForward(1), 1);

        //should have 2 enemies left
        assertEquals(enemyWave.getNumCurrEnemies(), 2);

        //System.out.println(Arrays.deepToString(enemyWave.getEnemyLocations().toArray()));

        assertArrayEquals(enemyWave.getEnemyLocations().toArray(), new int[][]{{4, 20}, {1, 10}});

        //move until only one enemy left which is at (0, 20)
        assertEquals(enemyWave.moveEnemiesForward(4), 1);

        assertEquals(enemyWave.getNumCurrEnemies(), 1);

        assertArrayEquals(enemyWave.getEnemyLocations().toArray(), new int[][]{{0, 20}});
    }

}
