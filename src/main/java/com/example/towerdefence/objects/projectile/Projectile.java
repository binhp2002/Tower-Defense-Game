package com.example.towerdefence.objects.projectile;

import com.example.towerdefence.objects.enemy.*;

public abstract class Projectile {
    private int damage;
    private int speed;
    private String imagePath;

    /**
     * most specific constructor for projectile
     * @param damage damage of projectile
     * @param speed speed of projectile
     * @param imagePath path to image of projectile
     */
    public Projectile(int damage, int speed, String imagePath) {
        this.damage = damage;
        this.speed = speed;
        this.imagePath = imagePath;
    }

    /**
     * gets the damage of the projectile
     * @return damage of projectile
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * sets the damage of the projectile, the damage of the projectile cannot be negative
     * @param damage the new damage of the enemy
     * @return 0 if the damage has been successfully set and -1 otherwise
     */
    public int setDamage(int damage) {
        if (damage < 0) {
            return -1;
        }
        this.damage = damage;
        return 0;
    }

    /**
     * returns the speed of the projectile
     * @return speed of the projectile
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * sets the speed of the projectile, which must be positive (otherwise projectile is stationary
     * or moving backwards)
     * @param speed new speed of projectile
     * @return 0 if speed is successfully changed and -1 otherwise
     */
    public int setSpeed(int speed) {
        if (speed <= 0) {
            return -1;
        }
        this.speed = speed;
        return 0;
    }

    /**
     * returns image path of the projectile
     * @return image path of the projectile
     */
    public String getImagePath() {
        return this.imagePath;
    }

    public abstract void hitTarget(Enemy e);
}
