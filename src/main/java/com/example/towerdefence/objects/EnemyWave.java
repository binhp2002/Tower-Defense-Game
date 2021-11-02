package com.example.towerdefence.objects;

import com.example.towerdefence.objects.enemy.*;

import java.util.*;

/**
 * wrapper class for the enemies in a wave
 */
public class EnemyWave {

    private ArrayList<Enemy> enemies;

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
    public void addEnemy(int x, int y) {
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
    public List<int[]> getEnemyLocations() {
        List<int[]> enemyLocations = new ArrayList<>();
        for (Enemy enemy: enemies) {
            //add the enemy locations to the list
            enemyLocations.add(enemy.getLocation());
        }
        return enemyLocations;
    }

    /**
     * get the enemies that are currently in the enemy wave
     * @return List of enemies
     */
    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * returns whether there are any enemies left in the wave
     * @return true if there are no enemies left and false if there are remaining enemies
     */
    public boolean isEmpty() {
        return this.enemies.isEmpty();
    }

    /**
     * move enemies forward (leftward, negative x direction) and
     * return number of enemies that reached past the zero line
     * @param steps number of steps to move the enemies to the left by
     * @return number of enemies that past 0 and are deleted
     */
    public List<Enemy> moveEnemiesForward(int steps) {
        List<Enemy> reachedEnemies = new ArrayList<>();

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            if (enemy.moveHorizontal(steps) == -1) {
                //delete the enemy since now moved past the end
                enemies.remove(enemy);
                reachedEnemies.add(enemy);
                //don't progress further, need to process this index again
                i--;
            }
        }

        return reachedEnemies;
    }

}
