package com.example.towerdefence.objects;

import com.example.towerdefence.objects.enemy.*;

import java.util.*;

/**
 * wrapper class for the enemies in a wave
 */
public class EnemyWave {

    ArrayList<Enemy> enemies;

    /**
     * initialize new wave
     */
    public EnemyWave() {
        enemies = new ArrayList<>();
    }

    /**
     * create an enemy at the specified (x, y) location
     *
     * @param x x coordinate of enemy spawn point
     * @param y y coordinate of enemy spawn point
     */
    public void createEnemy(int x, int y) {
        Enemy newEnemy = new BasicEnemy(x, y);
        enemies.add(newEnemy);
    }

    /**
     * return the number of enemies currently still present in the array
     * @return number of enemies still in enemies ArrayList
     */
    public int getNumCurrEnemies() {
        return enemies.size();
    }

    /**
     * returns an arraylist with the enemy locations
     * @return List with the enemy locations as int array elements in the List
     */
    public List<int []> getEnemyLocations() {
        List<int []> enemyLocations = new ArrayList<>();
        for (Enemy enemy: enemies) {
            //add the enemy locations to the list
            enemyLocations.add(enemy.getLocation());
        }
        return enemyLocations;
    }

    /**
     * move enemies forward and return number of enemies that reached past the zero line
     * @param steps number of steps to move the enemies to the left by
     * @return number of enemies that past 0 and are deleted
     */
    public int moveEnemiesForward(int steps) {
        int numMovedPastEnd = 0;
        for (Enemy enemy: enemies) {
            //move enemy and check for the return code
            if (enemy.moveHorizontal(steps) == -1) {
                //delete the enemy since now moved past the end
                enemies.remove(enemy);
                numMovedPastEnd++;
            }
        }
        return numMovedPastEnd;
    }

}
