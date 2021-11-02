package com.example.towerdefence.objects.projectile;

import com.example.towerdefence.objects.enemy.*;

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
