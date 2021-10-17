package com.example.towerdefence.objects;

public class SniperTower extends Tower {
    public static final String DESCRIPTION = "Attacks enemies with streams of explosive sniper bullets";
    public static final String NAME = "Sniper Tower";
    public static final int BASIC_COST = 200;

    public SniperTower() {
        super(100, 2, SniperProjectile.class, "");
    }

    public static int getBasicCost() {
        return SniperTower.BASIC_COST;
    }

    public static  String getName() {
        return SniperTower.NAME;
    }

    public static String getDescription() {
        return SniperTower.DESCRIPTION;
    }
}
