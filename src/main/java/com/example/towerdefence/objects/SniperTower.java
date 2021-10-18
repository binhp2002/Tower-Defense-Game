package com.example.towerdefence.objects;

public class SniperTower extends Tower {
    public static final String DESCRIPTION =
            "Attacks enemies with streams of explosive sniper bullets";
    public static final String NAME = "Sniper Tower";
    public static final int BASIC_COST = 200;
    public static final String IMAGE_PATH = "file:./target/classes/images/SniperTower.png";

    public SniperTower() {
        super(100, 2, SniperProjectile.class, "");
    }

    public int getBasicCost() {
        return SniperTower.BASIC_COST;
    }

    public String getName() {
        return SniperTower.NAME;
    }

    public String getDescription() {
        return SniperTower.DESCRIPTION;
    }

    public String getImagePath() {
        return SniperTower.IMAGE_PATH;
    }
}
