package com.example.towerdefence.objects.enemy;

public class TankEnemy extends Enemy {

    public static final String IMAGE_PATH = "file:./src/main/resources/images/tankEnemy.png";
    private static final int FULL_HEALTH = 200;
    private static final int REWARD = 20;
    private static final int HEIGHT = 20;
    private static final int WIDTH = 20;

    /**
     * create TankEnemy with default [0, 0] location
     */
    public TankEnemy() {
        this(0, 0);
    }

    /**
     * specify location of enemy to be created, default health of 200 and default damage of 20
     *
     * @param x x coordinate of enemy
     * @param y y coordinate of enemy
     */
    public TankEnemy(int x, int y) {
        super(FULL_HEALTH, 20, 5, x, y);
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
