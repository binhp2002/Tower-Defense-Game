package com.example.towerdefence.objects.tower;

import com.example.towerdefence.objects.EnemyWave;
import com.example.towerdefence.objects.enemy.Enemy;
import com.example.towerdefence.objects.projectile.*;

import java.lang.reflect.Constructor;

public abstract class Tower {
    private int health;
    private Class projectileType;
    private int rateOfFire;

    private int[] absoluteLocation;

    /**
     * most specific constructor, to set everything
     * @param health starting health of the tower
     * @param rateOfFire rate of fire of the tower
     * @param projectileType the class of the projectile that this tower fires
     * @param absoluteLocation the absolute location of the tower
     */
    public Tower(int health, int rateOfFire, Class projectileType, int[] absoluteLocation) {
        if (!Projectile.class.isAssignableFrom(projectileType)) {
            //ensure that projectileType implements projectile interface,
            //can assign projectileType to projectile
            throw new RuntimeException("projectileType argument must implement projectile");
        }
        this.health = health;
        this.projectileType = projectileType;
        this.rateOfFire = rateOfFire;
        this.absoluteLocation = absoluteLocation;
    }

    /**
     * factory method to create Tower from the tower class
     * @param towerClass class of tower to be instantiated
     * @return newly created Tower of towerClass
     */
    public static Tower createTower(Class towerClass, int x, int y) {
        if (!Tower.class.isAssignableFrom(towerClass)) {
            throw new IllegalArgumentException("towerClass is not a subclass of abstract"
                    + "class Tower");
        }
        try {
            return (Tower) towerClass.getConstructor((new int[]{}).getClass())
                    .newInstance(new int[]{x, y});
        } catch (Exception e) {
            throw new RuntimeException("Subclass of Tower does not have a constructor"
             + "that takes in int[] as argument (int[] represents location of tower in GridPane");
        }
    }

    /**
     * returns the basic cost of the tower, which should be implemented as a
     * static final variable in the subclasses of tower
     * @return returns basic cost of the tower
     */
    public abstract int getBasicCost();

    /**
     * returns the name of the tower, which should be implemented as a static
     * final variable in the subclasses of tower
     * @return returns the name of the tower
     */
    public abstract String getName();

    /**
     * returns the description of the tower
     * @return returns the tower description
     */
    public abstract String getDescription();

    /**
     * returns the image path of the tower
     * @return returns the tower image path
     */
    public abstract String getImagePath();

    /**
     * gets health of tower
     * @return health of tower
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * gets absolute location of tower
     * @return absolute location of tower
     */
    public int[] getAbsoluteLocation() {
        return this.absoluteLocation;
    }

    /**
     * sets absolute location of tower
     * @param absoluteLocation new absolute location of tower
     * @return 0 if absolute location of tower set successfully (x and y greater than or equal
     * to 0)
     */
    public int setAbsoluteLocation(int[] absoluteLocation) {
        if (absoluteLocation == null || absoluteLocation.length != 2 || absoluteLocation[0] < 0
                || absoluteLocation[1] < 0) {
            return -1;
        }
        this.absoluteLocation = absoluteLocation;
        return 0;
    }

    /**
     * sets health of tower and returns 0 if set. Does not set if health <= 0 and returns -1
     * @param health new health of the tower
     * @return 0 if health was successfully set, -1 if health was <= 0 and health was not set
     */
    public int setHealth(int health) {
        if (health <= 0) {
            return -1;
        }
        this.health = health;
        return 0;
    }

    /**
     * shoot projectile
     * @return projectile fired, null if nothing fired
     */
    public Projectile shoot() {
        //get constructure for projectile type
        Constructor<Projectile> projectileConstructor;
        try {
            projectileConstructor = projectileType.getConstructor();
            Projectile projectile = projectileConstructor.newInstance();
            return projectile;
        } catch (Exception e) {
            //don't do anything, just continue the game
            return null;
        }
    }

    public double euclideanDistance(int[] towerLocation, int[] enemyLocation) {
        double distance = Math.hypot(towerLocation[0]-enemyLocation[0],
                towerLocation[1]-enemyLocation[1]);
        return distance;
    }


    /**
     * calculates if enemyWave x-coordinate is within range of tower
     * @param enemyLocation is location of enemy
     * @return boolean if enemy is within range of tower
     */
    public boolean inRange(int[] enemyLocation) {
        int[] towerLocation = this.absoluteLocation;
        double distance = Math.hypot(towerLocation[0]-enemyLocation[0],
                towerLocation[1]-enemyLocation[1]);

        //placeholder value 100 for range
        if (distance < 100) {
            return true;
        } else {
            return false;
        }

    }
}
