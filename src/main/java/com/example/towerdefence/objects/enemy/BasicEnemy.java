package com.example.towerdefence.objects.enemy;

public class BasicEnemy extends Enemy {

    public static final String IMAGE_PATH = "file:./src/main/resources/images/enemy.png";
    private static final int FULL_HEALTH = 100;
    private static final int REWARD = 10;

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
        super(FULL_HEALTH, 10, 10, x, y);
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

    @Override
    public int getFullHealth() {
        return FULL_HEALTH;
    }

    @Override
    public int getReward() {
        return REWARD;
    }
}
