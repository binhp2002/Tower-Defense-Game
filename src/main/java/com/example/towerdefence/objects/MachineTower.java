package com.example.towerdefence.objects;

public class MachineTower extends Tower{
    public static final String DESCRIPTION = "Attacks enemies with rapid fire machine gun";
    public static final String NAME = "Machine Tower";
    public static final int BASIC_COST = 300;
    public static final String IMAGE_PATH = "file:./target/classes/images/MachineTower.png";

    /**
     * machine tower fires SmallProjectiles more rapidly than basic tower
     */
    public MachineTower() {

        super(100, 5, SmallProjectile.class, "");
    }

    public static int getBasicCost() {
        return MachineTower.BASIC_COST;
    }

    public static String getName() {
        return MachineTower.NAME;
    }

    public static String getDescription() {
        return MachineTower.DESCRIPTION;
    }

    public static String getImagePath() {
        return MachineTower.IMAGE_PATH;
    }
}
