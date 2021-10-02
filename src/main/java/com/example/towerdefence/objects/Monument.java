package com.example.towerdefence.objects;

public class Monument {

    private int health;

    /**
     * intialize Monument object with -1 health
     */
    public Monument() {
        this.health = -1;
    }

    /**
     * return health of monument
     * @return monument's health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * sets health of monument (health must be non-negative)
     * @param health new health of monument
     * @return 0 if successfully changed health of monument, -1 otherwise
     */
    public int setHealth(int health) {
        if (health == 0) {
            return -1;
        }
        this.health = health;
        return 0;
    }
}
