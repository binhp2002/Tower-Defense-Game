package com.example.towerdefence.objects;

public class BasicTower extends Tower {
    public static final String DESCRIPTION = "";
    public static final String NAME = "Basic Tower";
    public static final int BASIC_COST = 100;

    public BasicTower() {
        super(100, 1, NormalProjectile.class, "");
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
}
