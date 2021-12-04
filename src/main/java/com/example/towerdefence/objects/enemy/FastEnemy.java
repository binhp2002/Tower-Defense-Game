package com.example.towerdefence.objects.enemy;

public class FastEnemy extends Enemy {
    public static final String IMAGE_PATH = "file:./src/main/resources/images/fastEnemy.png";
    private static final int FULL_HEALTH = 50;
    private static final int REWARD = 5;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    /**
     * create FastEnemy with default [0, 0] location
     */
    public FastEnemy() {
        this(0, 0);
    }

    /**
     * specify location of enemy to be created, default health of 100 and default damage of 10
     * @param x x coordinate of enemy
     * @param y y coordinate of enemy
     */
    public FastEnemy(int x, int y) {
        super(FULL_HEALTH, 5, 20, x, y);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
