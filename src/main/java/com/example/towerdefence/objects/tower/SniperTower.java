package com.example.towerdefence.objects.tower;


public class SniperTower extends Tower {
    public static final String DESCRIPTION =
            "Attacks enemies with streams of explosive sniper bullets";
    public static final String NAME = "Sniper tower";
    public static final int BASIC_COST = 200;
    public static final String IMAGE_PATH1 = "file:./src/main/resources/images/SniperTower.png";
    public static final String IMAGE_PATH2 = "file:./src/main/resources/images/UpgradedSniperTower.png";
    public static int damage = 50;

    public SniperTower(int[] absoluteLocation) {
        super(100, 2, absoluteLocation, 500, IMAGE_PATH1);
    }

    @Override
    public int getBasicCost() {
        return SniperTower.BASIC_COST;
    }

    @Override
    public String getName() {
        return SniperTower.NAME;
    }

    @Override
    public String getDescription() {
        return SniperTower.DESCRIPTION;
    }

    @Override
    public String getImagePath2() { return SniperTower.IMAGE_PATH2;}

    @Override
    public int getDamage() {
        return SniperTower.damage;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }
}
