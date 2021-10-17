package com.example.towerdefence.objects;

public class BasicTower extends Tower {
    public static final String DESCRIPTION = "Attacks enemies with a stream of bullets";
    public static final String NAME = "Basic Tower";
    public static final int BASIC_COST = 100;
    public static final String IMAGE_PATH = "file:./target/classes/images/BasicTower.png";

    public BasicTower() {
        super(100, 1, NormalProjectile.class, "");
    }

    public static int getBasicCost() {
        return BasicTower.BASIC_COST;
    }

    public static String getName() {
        return BasicTower.NAME;
    }

    public static String getDescription() {
        return BasicTower.DESCRIPTION;
    }

    public static String getImagePath() {
        return BasicTower.IMAGE_PATH;
    }
}
