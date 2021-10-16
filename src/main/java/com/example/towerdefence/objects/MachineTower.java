package com.example.towerdefence.objects;

public class MachineTower extends Tower{
    /**
     * machine tower fires SmallProjectiles more rapidly than basic tower
     */
    public MachineTower() {
        super(100, 5, SmallProjectile.class, "", 100);
    }
}
