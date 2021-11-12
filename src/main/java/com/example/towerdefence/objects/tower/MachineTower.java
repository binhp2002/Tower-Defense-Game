package com.example.towerdefence.objects.tower;

import com.example.towerdefence.objects.projectile.*;

public class MachineTower extends Tower {
    public static final String DESCRIPTION = "Attacks enemies with rapid fire machine gun";
    public static final String NAME = "Machine tower";
    public static final int BASIC_COST = 300;
    public static final String IMAGE_PATH = "file:./src/main/resources/images/MachineTower.png";

    /**
     * machine tower fires SmallProjectiles more rapidly than basic tower
     */
    public MachineTower(int[] absoluteLocation) {

        super(100, 5, SmallProjectile.class, absoluteLocation);
    }

    public int getBasicCost() {
        return MachineTower.BASIC_COST;
    }

    public String getName() {
        return MachineTower.NAME;
    }

    public String getDescription() {
        return MachineTower.DESCRIPTION;
    }

    public String getImagePath() {
        return MachineTower.IMAGE_PATH;
    }
}
