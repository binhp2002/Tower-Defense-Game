package com.example.towerdefence.tests;

import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.enemy.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class EnemyWaveTest {

    private EnemyWave enemyWave;

    @Before
    public void setUp() {
        enemyWave = new EnemyWave();
        //verify that there are no enemies at the start
        assertEquals(enemyWave.getNumCurrEnemies(), 0);
    }

    @Test
    public void testCreateEnemy() {
        enemyWave.addEnemy(BasicEnemy.class, 10, 20);
        //check that an enemy has been added
        assertEquals(enemyWave.getNumCurrEnemies(), 1);
        List<int[]> enemyLocations = enemyWave.getEnemyRelativeLocations();
        //make sure there's only one location
        assertEquals(enemyWave.getEnemyRelativeLocations().size(), 1);
        //check that the location is the same (x, y)
        assertArrayEquals(enemyLocations.get(0), new int[]{10, 20});
    }

    @Test
    public void testMoveEnemiesForwardSteps() {
        int[][] enemyLocations = new int[][]{{10, 20}, {5, 6}, {7, 10}};
        for (int[] enemyLocation: enemyLocations) {
            //add the enemies to enemyWave with location as enemyLocation
            enemyWave.addEnemy(BasicEnemy.class, enemyLocation[0], enemyLocation[1]);
        }
        //check that 3 enemies are inside
        assertEquals(enemyWave.getNumCurrEnemies(), 3);
        
        //no enemies should move past the end
        assertArrayEquals(enemyWave.moveEnemiesForward(5).toArray(), new Enemy[]{});


        //the enemy that is going to be removed is the {5, 6} which is at index 1
        Enemy[] removedEnemies1 = new Enemy[]{enemyWave.getEnemies().get(1)};

        //one enemy moves past the end
        assertArrayEquals(enemyWave.moveEnemiesForward(1).toArray(), removedEnemies1);

        //should have 2 enemies left
        assertEquals(enemyWave.getNumCurrEnemies(), 2);

        //System.out.println(Arrays.deepToString(enemyWave.getEnemyLocations().toArray()));

        assertArrayEquals(enemyWave.getEnemyRelativeLocations().toArray(), new int[][]{{4, 20}, {1, 10}});

        //Enemy that is going to be removed is originally {7, 10}, which is now at index 1
        Enemy[] removedEnemies2 = new Enemy[]{enemyWave.getEnemies().get(1)};

        //move until only one enemy left which is at (0, 20)
        assertArrayEquals(enemyWave.moveEnemiesForward(4).toArray(), removedEnemies2);

        assertEquals(enemyWave.getNumCurrEnemies(), 1);

        assertArrayEquals(enemyWave.getEnemyRelativeLocations().toArray(), new int[][]{{0, 20}});

        //only one enemy left
        Enemy[] removedEnemies3 = new Enemy[]{enemyWave.getEnemies().get(0)};

        assertArrayEquals(enemyWave.moveEnemiesForward(1).toArray(), removedEnemies3);

        //check that enemy wave is empty after last enemy has moved past the monument line
        assertTrue(enemyWave.isEmpty());
    }

    /**
     * check if isEmpty returns false when enemyWave is first initialized and array is empty
     */
    @Test
    public void testIsEmptyWhenEmpty() {
        assertTrue(enemyWave.isEmpty());
    }

    /**
     * check if isEmpty return false when emptyWave has enemies
     */
    @Test
    public void testIsEmptyWhenNotEmpty() {
        assertTrue(enemyWave.isEmpty());
        enemyWave.addEnemy(BasicEnemy.class, 5, 5);
        assertFalse(enemyWave.isEmpty());
    }

}
