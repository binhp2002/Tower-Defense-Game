package com.example.towerdefence.objects.enemy;

public class TankEnemy extends Enemy{

    public static final String IMAGE_PATH = "file:./src/main/resources/images/enemy.png";

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
        super(200, 20, 10, x, y);
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }


}
