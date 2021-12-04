package com.example.towerdefence.objects.tower;


public class BasicTower extends Tower {
    public static final String DESCRIPTION = "Attacks enemies with a stream of bullets";
    public static final String NAME = "Basic tower";
    public static final int BASIC_COST = 100;
    public static final String IMAGE_PATH1 = "file:./src/main/resources/images/BasicTower.png";
    public static final String IMAGE_PATH2 = "file:./src/main/resources/images/UpgradedBasicTower.png";
    public static int damage = 20;

    public BasicTower(int[] absoluteLocation) {
        super(100, 5, absoluteLocation, 250, IMAGE_PATH1);

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
    public String getImagePath2() { return BasicTower.IMAGE_PATH2;}

    @Override
    public int getDamage() {
        return BasicTower.damage;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }
}
