package com.example.towerdefence.objects;

public class NormalProjectile extends Projectile {

    /**
     * constructor for NormalProjectile which has damage 10, speed 1 and "" as imagePath
     */
    public NormalProjectile() {
        super(10, 1, "");
    }

    @Override
    public void hitTarget(Enemy e) { }
}
