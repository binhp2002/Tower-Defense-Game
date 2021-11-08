package com.example.towerdefence.objects.enemy;

public class BasicEnemy extends Enemy {

    public static final String IMAGE_PATH = "file:./src/main/resources/images/enemy.png";

    /**
     * create BasicEnemy with default [0, 0] location
     */
    public BasicEnemy() {
        this(0, 0);
    }

    /**
     * specify location of enemy to be created, default health of 100 and default damage of 10
     * @param x x coordinate of enemy
     * @param y y coordinate of enemy
     */
    public BasicEnemy(int x, int y) {
        super(100, 10, 10, x, y);
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
}
