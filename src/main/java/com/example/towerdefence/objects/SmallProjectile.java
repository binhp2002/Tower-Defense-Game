package com.example.towerdefence.objects;

public class SmallProjectile extends Projectile {

    /**
     * constructor for SmallProjectile which has damage 5, speed 2 and "" as imagePath
     */
    public SmallProjectile() {
        super(5, 2, "");
    }

    @Override
    public void hitTarget(Enemy e) { }

}
