package com.example.towerdefence.objects.enemy;

import java.util.*;

public abstract class Enemy {
    private int health;
    private int damage;
    private int speed;
    //location of the enemy relative to the game path
    private int[] relativeLocation;

    /**
     * initialize enemy with the health and damage provided, sets default location to [0, 0]
     * @param health enemy's health
     * @param damage enemy's damage
     * @param speed enemy's speed
     */
    public Enemy(int health, int damage, int speed) {
        this(health, damage, speed, 0, 0);
    }

    /**
     * initialize enemy with the health, damage, and location provided
     * @param health enemy's health
     * @param damage enemy's damage
     * @param speed enemy's speed
     * @param x enemy x-coordinate relative to the game path
     * @param y enemy y-coordinate relative to the game path
     */
    public Enemy(int health, int damage, int speed, int x, int y) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.relativeLocation = new int[]{x, y};
    }


    /**
     * returns health of the enemy
     * @return health of the enemy
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * set the health of the enemy, return 0 if successfully changed and -1 if new health
     * is <= 0, health set to 0
     * @param health new health of enemey
     * @return 0 if health is successfully changed and -1 if not
     */
    public int setHealth(int health) {
        if (health <= 0) {
            //decrease health to 0
            this.health = 0;
            return -1;
        }
        this.health = health;
        return 0;
    }

    /**
     * returns damage of enemy
     * @return damage of enemy
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * sets the damage of the enemy, only allows non-negative damage
     * @param damage new damage of enemy
     * @return 0 if successfully set damage, -1 if damage was negative and damage not changed
     */
    public int setDamage(int damage) {
        if (damage < 0) {
            //enemy damage cannot be negative
            return -1;
        }
        this.damage = damage;
        return 0;
    }

    /**
     * returns speed of enemy
     * @return enemy's speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * sets the speed of the enemy, speed must be a positive value
     * @param speed new speed of enemy
     * @return returns 0 if speed successfully changed, -1 if speed was non-positive
     */
    public int setSpeed(int speed) {
        if (speed <= 0) {
            //speed must be positive
            return -1;
        }
        this.speed = speed;
        return 0;
    }

    /**
     * returns the location of the enemy
     * @return location of the enemy
     */
    public int[] getRelativeLocation() {
        return Arrays.copyOf(this.relativeLocation, this.relativeLocation.length);
    }

    /**
     * sets the location of the enemy
     * @param relativeLocation location of enemy
     * @return 0 if location of enemy successfully changed, -1 otherwise
     */
    public int setRelativeLocation(int[] relativeLocation) {
        if (relativeLocation.length != 2) {
            //check that the length of the location array is 2
            return -1;
        }
        this.relativeLocation = relativeLocation;
        return 0;
    }

    /**
     * moves the enemy n number of steps to the left (left is negative), if n is negative
     * then move to the right
     * @param steps moves the enemy to the left
     * @return 0 if enemy can be moved to that location, -1 if enemy cannot be moved there
     * due to location being less than 0
     */
    public int moveHorizontal(int steps) {
        if (this.relativeLocation[0] < steps) {
            //only want to return if this.location[0] - steps < 0, so enemy can still
            //be at 0
            return -1;
        }
        //subtract that number of steps from the x-coordinate and move there
        this.relativeLocation[0] -= steps;
        return 0;
    }


    /**
     * returns the image path of the enemy
     * @return image path of the enemy
     */
    public abstract String getImagePath();

    /**
     * returns the enemy's max health based off type
     * @return int of a enemy's full health based off type
     */
    public abstract int getFullHealth();

    /**
     * returns the amount of reward earned by killing the enemy
     * @return money earned
     */
    public abstract int getReward();

    /**
     * get width of enemy
     * @return width of the enemy image
     */
    public abstract int getWidth();

    /**
     * get height of enemy
     * @return height of the enemy image
     */
    public abstract int getHeight();

}
