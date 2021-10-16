package com.example.towerdefence.objects;

public class SniperTower extends Tower {
    public static final String DESCRIPTION = "";
    public static final String NAME = "Sniper Tower";
    public static final int BASIC_COST = 100;

    public SniperTower() {
        super(100, 1, SniperProjectile.class, "");
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
}
