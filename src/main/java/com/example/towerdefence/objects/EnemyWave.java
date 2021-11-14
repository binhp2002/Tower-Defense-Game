package com.example.towerdefence.objects;

import com.example.towerdefence.objects.enemy.*;

import java.util.*;

/**
 * wrapper class for the enemies in a wave
 */
public class EnemyWave {

    private ArrayList<Enemy> enemies;

    private int[] originLocation;

    /**
     * initialize new wave with originLocation of the game path set as originLocation
     * @param originLocation origin location of game path
     */
    public EnemyWave(int[] originLocation) {
        this.enemies = new ArrayList<>();
        this.originLocation = originLocation;
    }

    /**
     * create an enemy at the specified (x, y) location, which is the
     * relative location of the enemy with (0, 0) as the top left corner of the game path
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
            newEnemy = (Enemy) enemyClass.getConstructor(Integer.TYPE, Integer.TYPE)
                    .newInstance(x, y);
        } catch (Exception e) {
            System.out.println("Exception in EnemyWave.addEnemy when creating instance of "
                    + enemyClass);
            System.out.println(e);
        }
        enemies.add(newEnemy);
    }

    /**
     * adds enemy to EnemyWave
     *
     * @param enemy enemy to be added
     */
    public void addEnemy(Enemy enemy) {
        if (enemy == null) {
            throw new RuntimeException("Cannot add empty enemy in addEnemy");
        }
        enemies.add(enemy);
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
    public List<int[]> getEnemyRelativeLocations() {
        List<int[]> enemyLocations = new ArrayList<>();
        for (Enemy enemy: enemies) {
            //add the enemy locations to the list
            enemyLocations.add(enemy.getRelativeLocation());
        }
        return enemyLocations;
    }

    /**
     * get absolute enemy locations relative to the screen
     * @return List of absolute enemy locations
     */
    public List<int[]> getEnemyAbsoluteLocations() {
        List<int[]> enemyLocations = new ArrayList<>();
        for (Enemy enemy: enemies) {
            int[] currEnemyLocation = enemy.getRelativeLocation();
            for (int i = 0; i < originLocation.length; i++) {
                //add the origin location to the relative location to get
                //the absolute location
                currEnemyLocation[i] += originLocation[i];
            }
            enemyLocations.add(currEnemyLocation);
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
     * sets originLocation, returns 0 to indicate successful method call
     * @param originLocation new origin location
     * @return 0
     */
    public int setOriginLocation(int[] originLocation) {
        this.originLocation = originLocation;
        return 0;
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
     * @param steps number of steps (pixels) to move enemies by
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

    /**
     * gets the enemy at the specified index
     * @param index index to get enemy from
     * @return Enemy object at that index
     */
    public Enemy getEnemy(int index) {
        if (index < 0 || index >= this.enemies.size()) {
            throw new IllegalArgumentException("Index passed into getEnemy out of range, size "
                    + this.enemies.size() + " but trying to access index " + index);
        }
        return this.enemies.get(index);
    }

    /**
     * does damage to specified enemy and removes from this.enemies list if enemy is killed
     * @param index index of enemy to do damage to
     * @param damage damage to be inflicted on enemy
     * @return Enemy if the enemy was killed, otherwise null
     */
    public Enemy doDamage(int index, int damage) {
        //get the enemy
        Enemy currEnemy = this.getEnemy(index);
        if (currEnemy.setHealth(currEnemy.getHealth() - damage) == -1) {
            //remove the current enemy from this.enemies
            Enemy deadEnemy = this.enemies.get(index);
            this.enemies.remove(index);
            return deadEnemy;
        }
        return null;
    }

}
