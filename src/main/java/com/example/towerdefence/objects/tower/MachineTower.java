package com.example.towerdefence.objects.tower;

import com.example.towerdefence.objects.projectile.*;

public class MachineTower extends Tower {
    public static final String DESCRIPTION = "Attacks enemies with rapid fire machine gun";
    public static final String NAME = "Machine tower";
    public static final int BASIC_COST = 300;
    public static final String IMAGE_PATH = "file:./src/main/resources/images/MachineTower.png";
    public static final int damage = 5;

    /**
     * machine tower fires SmallProjectiles more rapidly than basic tower
     * @param absoluteLocation absolute location of the MachineTower in the Scene
     */
    public MachineTower(int[] absoluteLocation) {

        super(100, 5, SmallProjectile.class, absoluteLocation, 200);
    }
    @Override
    public int getBasicCost() {
        return MachineTower.BASIC_COST;
    }

    @Override
    public String getName() {
        return MachineTower.NAME;
    }

    @Override
    public String getDescription() {
        return MachineTower.DESCRIPTION;
    }

    @Override
    public String getImagePath() {
        return MachineTower.IMAGE_PATH;
    }

    @Override
    public int getDamage() {
        return MachineTower.damage;
    }
}
