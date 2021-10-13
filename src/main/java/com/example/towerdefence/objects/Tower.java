package com.example.towerdefence.objects;

import java.lang.reflect.Constructor;

public abstract class Tower {
    int health;
    Class<Projectile> projectileType;
    int rateOfFire;
    String imagePath;

    /**
     * most specific constructor, to set everything
     * @param health starting health of the tower
     * @param rateOfFire rate of fire of the tower
     * @param imagePath image of the tower
     * @param projectileType the class of the projectile that this tower fires
     */
    public Tower(int health, int rateOfFire, Class<Projectile> projectileType, String imagePath) {
        this.health = health;
        this.projectileType = projectileType;
        this.rateOfFire = rateOfFire;
        this.imagePath = imagePath;
    }

    /**
     * default constructor, only need to set projectile and image path for the tower
     * @param imagePath image of the tower
     * @param projectileType the class of the projectile that this tower fires
     */
    public Tower(Class<Projectile> projectileType, String imagePath) {
        this.imagePath = imagePath;
        this.projectileType = projectileType;
    }

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
     */
    public void shoot() {
        //get constructure for projectile type
        Constructor<Projectile> projectileConstructor;
        try {
            projectileConstructor = projectileType.getConstructor();
            Projectile projectile = projectileConstructor.newInstance();
        } catch (Exception nsme) {
            //don't do anything, just continue the game
            return;
        }
    }


}
