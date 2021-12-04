package com.example.towerdefence.objects.tower;

public abstract class Tower {
    private int health;
    private int rateOfFire;
    private int range;
    private long lastFired;
    private String imagePath;

    private int level = 1;

    private int[] absoluteLocation;

    /**
     * most specific constructor, to set everything
     * @param health starting health of the tower
     * @param rateOfFire rate of fire of the tower
     * @param absoluteLocation the absolute location of the tower
     * @param range range of tower
     */
    public Tower(int health, int rateOfFire, int[] absoluteLocation, int range, String imagePath) {
        this.health = health;
        this.rateOfFire = rateOfFire;
        this.absoluteLocation = absoluteLocation;
        this.range = range;
        this.imagePath = imagePath;
    }

    /**
     * factory method to create Tower from the tower class
     * @param towerClass class of tower to be instantiated
     * @param x absolute x location of tower
     * @param y absolute y location of tower
     * @return newly created Tower of towerClass
     */
    public static Tower createTower(Class towerClass, int x, int y) {
        if (!Tower.class.isAssignableFrom(towerClass)) {
            throw new IllegalArgumentException("towerClass is not a subclass of abstract"
                    + "class Tower");
        }
        try {
            return (Tower) towerClass.getConstructor((new int[]{}).getClass())
                    .newInstance(new int[]{x, y});
        } catch (Exception e) {
            throw new RuntimeException("Subclass of Tower does not have a constructor"
             + "that takes in int[] as argument (int[] represents location of tower in GridPane");
        }
    }

    /**
     * returns the basic cost of the tower, which should be implemented as a
     * static final variable in the subclasses of tower
     * @return returns basic cost of the tower
     */
    public abstract int getBasicCost();

    /**
     * returns the name of the tower, which should be implemented as a static
     * final variable in the subclasses of tower
     * @return returns the name of the tower
     */
    public abstract String getName();

    /**
     * returns the description of the tower
     * @return returns the tower description
     */
    public abstract String getDescription();

    /**
     * returns the image path of the tower
     * @return returns the tower image path
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * returns image path for upgraded tower
     * @ return returns upgraded tower image path
     */
    public abstract String getImagePath2();

    /**
     * gets health of tower
     * @return health of tower
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * gets absolute location of tower
     * @return absolute location of tower
     */
    public int[] getAbsoluteLocation() {
        return this.absoluteLocation;
    }

    /**
     * sets absolute location of tower
     * @param absoluteLocation new absolute location of tower
     * @return 0 if absolute location of tower set successfully (x and y greater than or equal
     * to 0)
     */
    public int setAbsoluteLocation(int[] absoluteLocation) {
        if (absoluteLocation == null || absoluteLocation.length != 2 || absoluteLocation[0] < 0
                || absoluteLocation[1] < 0) {
            return -1;
        }
        this.absoluteLocation = absoluteLocation;
        return 0;
    }

    /**
     * sets health of tower and returns 0 if set. Does not set if health <= 0 and returns -1
     * @param health new health of the tower
     * @return 0 if health was successfully set, -1 if health was <= 0 and health was not set
     */
    public int setHealth(int health) {
        if (health <= 0) {
            return -1;
        }
        this.health = health;
        return 0;
    }

    /**
     * calculates if enemyWave is within range of tower
     * @param enemyLocation is location of enemy
     * @return boolean if enemy is within range of tower
     */
    public boolean inRange(int[] enemyLocation) {
        int[] towerLocation = this.absoluteLocation;

        double distance = Math.hypot(towerLocation[0] - enemyLocation[0],
                towerLocation[1] - enemyLocation[1]);

        return distance <= this.getRange();
    }

    public int getRange() {
        return this.range;
    }
    
    public abstract int getDamage();

    public abstract void setDamage(int damage);

    public int getRateOfFire() {
        return this.rateOfFire;
    };

    public long getLastFired() {
        return this.lastFired;
    }

    public void setLastFired(long lastFired) {
        if (lastFired < 0) {
            throw new IllegalArgumentException("lastFired cannot be negative");
        }
        this.lastFired = lastFired;
    }

    /**
     * upgrades the tower if the level is 1, if the tower is 2 then don't upgrade (can only upgrade to level 2)
     * @return 0 if successfully upgraded, -1 otherwise
     */
    public int upgradeTower() {
        if (level != 1) {
            //don't upgrade return -1
            return -1;
        }
        //double the damage when upgrading
        this.setDamage(this.getDamage() * 5);
        this.imagePath = this.getImagePath2();
        this.level++;
        return 0;
    }

    public int getLevel() {
        return this.level;
    }
}
