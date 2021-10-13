package com.example.towerdefence.objects;

public class Enemy {
    private int health;
    private int damage;

    /**
     * initializes default enemy with damage 10 and health of 100
     */
    public Enemy() {
        this.health = 100;
        this.damage = 1;
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

}
