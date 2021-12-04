package com.example.towerdefence.objects.tower;

public class MachineTower extends Tower {
    public static final String DESCRIPTION = "Attacks enemies with rapid fire machine gun";
    public static final String NAME = "Machine tower";
    public static final int BASIC_COST = 300;
    public static final String IMAGE_PATH1 = "file:./src/main/resources/images/MachineTower.png";
    public static final String IMAGE_PATH2 = "file:./src/main/resources/images/UpgradedMachineTower.png";
    public static int damage = 5;

    /**
     * machine tower fires SmallProjectiles more rapidly than basic tower
     * @param absoluteLocation absolute location of the MachineTower in the Scene
     */
    public MachineTower(int[] absoluteLocation) {

        super(100, 20, absoluteLocation, 250, IMAGE_PATH1);
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

    @Override
    public String getImagePath2() { return MachineTower.IMAGE_PATH2;}

    @Override
    public int getDamage() {
        return MachineTower.damage;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }
}
