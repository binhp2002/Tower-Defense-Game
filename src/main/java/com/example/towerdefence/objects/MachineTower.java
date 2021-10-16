package com.example.towerdefence.objects;

public class MachineTower extends Tower{
    public static final String DESCRIPTION = "";
    public static final String NAME = "Machine Tower";
    public static final int BASIC_COST = 100;

    /**
     * machine tower fires SmallProjectiles more rapidly than basic tower
     */
    public MachineTower() {

        super(100, 5, SmallProjectile.class, "");
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
}
