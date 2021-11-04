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
     * @param enemyClass class of enemy to be added
     * @param x x coordinate of enemy spawn point
     * @param y y coordinate of enemy spawn point
     */
    public void addEnemy(Class enemyClass, int x, int y) {
        if (enemyClass == null || !Enemy.class.isAssignableFrom(enemyClass)) {
            //check that enemyClass is a subclass of Enemy
            throw new RuntimeException("enemyClass argument in addEnemy should be a subclass of "
                    + "abstract class Enemy");
        }
        Enemy newEnemy = null;
        try {
            //call the constructor and create instances of the enemy class
            newEnemy = (Enemy) enemyClass.getConstructor(Integer.TYPE, Integer.TYPE).newInstance(x, y);
        } catch (Exception e) {
            System.out.println("Exception in EnemyWave.addEnemy when creating instance of " + enemyClass);
            System.out.println(e);
        }
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
     * move enemies forward (leftward, negative x direction) for one round and
     * return number of enemies that reached past the zero line
     * distance moved by enemy depends on their speed
     * @return List of enemies that crossed zero line and were deleted
     */
    public List<Enemy> moveEnemiesForward() {
        List<Enemy> reachedEnemies = new ArrayList<>();

        for (int i = 0; i < enemies.size(); i++) {
            //iterate through all the enemies
            Enemy enemy = enemies.get(i);

            if (enemy.moveHorizontal(enemy.getSpeed()) == -1) {
                //delete the enemy since now moved past the end
                enemies.remove(enemy);
                reachedEnemies.add(enemy);
                //don't progress further, need to process this index again
                i--;
            }
        }

        return reachedEnemies;
    }

    /**
     * move enemies forward (leftward, negative x direction) by a set number of steps (pixels)
     * and return enemies that reached past the zero line
     * @return List of enemies that crossed zero line and were deleted
     */
    public List<Enemy> moveEnemiesForward(int steps) {
        List<Enemy> reachedEnemies = new ArrayList<>();

        for (int i = 0; i < enemies.size(); i++) {
            //iterate through all the enemies
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
