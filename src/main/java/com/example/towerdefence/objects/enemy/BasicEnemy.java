package com.example.towerdefence.objects.enemy;

public class BasicEnemy extends Enemy{

    public static final String IMAGE_PATH = "";

    public BasicEnemy() {
        super(100, 1);
    }

    public String getImagePath() { return IMAGE_PATH;}
}
