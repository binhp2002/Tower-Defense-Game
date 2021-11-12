package com.example.towerdefence.objects.tower;

import com.example.towerdefence.objects.projectile.*;

public class SniperTower extends Tower {
    public static final String DESCRIPTION =
            "Attacks enemies with streams of explosive sniper bullets";
    public static final String NAME = "Sniper tower";
    public static final int BASIC_COST = 200;
    public static final String IMAGE_PATH = "file:./src/main/resources/images/SniperTower.png";
    public static final int damage = 20;

    public SniperTower(int[] absoluteLocation) {
        super(100, 2, SniperProjectile.class, absoluteLocation,300);
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
    public String getImagePath() {
        return SniperTower.IMAGE_PATH;
    }

    @Override
    public int getDamage() {
        return SniperTower.damage;
    }
}
