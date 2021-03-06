package com.example.towerdefence.objects.tower;

import com.example.towerdefence.objects.projectile.*;

import java.lang.reflect.Constructor;

public abstract class Tower {
    private int health;
    private Class projectileType;
    private int rateOfFire;

    /**
     * most specific constructor, to set everything
     * @param health starting health of the tower
     * @param rateOfFire rate of fire of the tower
     * @param projectileType the class of the projectile that this tower fires
     */
    public Tower(int health, int rateOfFire, Class projectileType) {
        if (!Projectile.class.isAssignableFrom(projectileType)) {
            //ensure that projectileType implements projectile interface,
            //can assign projectileType to projectile
            throw new RuntimeException("projectileType argument must implement projectile");
        }
        this.health = health;
        this.projectileType = projectileType;
        this.rateOfFire = rateOfFire;
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
}
