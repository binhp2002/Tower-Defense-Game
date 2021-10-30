package com.example.towerdefence.objects.enemy;

public abstract class Enemy {
    private int health;
    private int damage;

    private int[] location;

    /**
     * initialize enemy with the health and damage provided, sets default location to [0, 0]
     */
    public Enemy(int health, int damage) {
        this.health = health;
        this.damage = damage;
        this.location = new int[]{0, 0};
    }

    /**
     * initialize enemy with the health, damage, and location provided
     */
    public Enemy(int health, int damage, int x, int y) {
        this.health = health;
        this.damage = damage;
        this.location = new int[]{x, y};
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
     * is <= 0, which is invalid
     * @param health new health of enemey
     * @return 0 if health is successfully changed and -1 if not
     */
    public int setHealth(int health) {
        if (health <= 0) {
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
     * returns the location of the enemy
     * @return location of the enemy
     */
    public int[] getLocation() {
        return this.location;
    }

    /**
     * sets the location of the enemy
     * @return 0 if location of enemy successfully changed, -1 otherwise
     */
    public int setLocation(int[] location) {
        if (location.length != 2) {
            //check tha the length of the location array is 2
            return -1;
        }
        this.location = location;
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
        if (this.location[0] < steps) {
            //only want to return if this.location[0] - steps < 0, so enemy can still
            //be at 0
            return -1;
        }
        //subtract that number of steps from the x-coordinate and move there
        this.location[0] -= steps;
        return 0;
    }


    /**
     * returns the image path of the enemy
     * @return image path of the enemy
     */
    public abstract String getImagePath();

}
