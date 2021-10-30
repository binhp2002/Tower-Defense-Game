package com.example.towerdefence.objects.enemy;

public class BasicEnemy extends Enemy{

    public static final String IMAGE_PATH = "";

    /**
     * create BasicEnemy with default [0, 0] location
     */
    public BasicEnemy() {
        super(0, 0);
    }

    /**
     * specify location of enemy to be created, default health of 100 and default damage of 1
     * @param x x coordinate of enemy
     * @param y y coordinate of enemy
     */
    public BasicEnemy(int x, int y) {
        super(100, 1, x, y);
    }

    public String getImagePath() { return IMAGE_PATH;}
}
