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
     * sets health of monument
     * @param health new health of monument
     * @return 0 to indicate health of monument successfully changed
     */
    public int setHealth(int health) {
        this.health = health;
        return 0;
    }
}
