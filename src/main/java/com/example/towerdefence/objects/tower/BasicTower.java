package com.example.towerdefence.objects.tower;


public class BasicTower extends Tower {
    public static final String DESCRIPTION = "Attacks enemies with a stream of bullets";
    public static final String NAME = "Basic tower";
    public static final int BASIC_COST = 100;
    public static final String IMAGE_PATH = "file:./src/main/resources/images/BasicTower.png";
    public static final int damage = 100;

    public BasicTower(int[] absoluteLocation) {
        super(100, 5, absoluteLocation, 250);
    }

    @Override
    public int getBasicCost() {
        return BasicTower.BASIC_COST;
    }

    @Override
    public String getName() {
        return BasicTower.NAME;
    }

    @Override
    public String getDescription() {
        return BasicTower.DESCRIPTION;
    }

    @Override
    public String getImagePath() {
        return BasicTower.IMAGE_PATH;
    }

    @Override
    public int getDamage() {
        return BasicTower.damage;
    }
}
