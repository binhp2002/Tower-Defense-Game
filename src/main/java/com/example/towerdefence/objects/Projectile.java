package com.example.towerdefence.objects;

public interface Projectile {
    int damage = -1;
    int speed = -1;
    String imagePath = null;

    void hitTarget(Enemy e);
}
