package com.example.towerdefence.objects.projectile;

import com.example.towerdefence.objects.enemy.*;

public class SniperProjectile extends Projectile {

    /**
     * constructor for NormalProjectile which has damage 10, speed 1 and "" as imagePath
     */
    public SniperProjectile() {
        super(20, 2, "");
    }

    @Override
    public void hitTarget(Enemy e) { }

}
