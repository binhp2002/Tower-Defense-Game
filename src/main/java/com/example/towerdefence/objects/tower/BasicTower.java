package com.example.towerdefence.objects.tower;

import com.example.towerdefence.objects.projectile.*;

public class BasicTower extends Tower {
    public static final String DESCRIPTION = "Attacks enemies with a stream of bullets";
    public static final String NAME = "Basic tower";
    public static final int BASIC_COST = 100;
    public static final String IMAGE_PATH = "file:./src/main/resources/images/BasicTower.png";

    public BasicTower() {
        super(100, 1, NormalProjectile.class);
    }

    public int getBasicCost() {
        return BasicTower.BASIC_COST;
    }

    public String getName() {
        return BasicTower.NAME;
    }

    public String getDescription() {
        return BasicTower.DESCRIPTION;
    }

    public String getImagePath() {
        return BasicTower.IMAGE_PATH;
    }
}
