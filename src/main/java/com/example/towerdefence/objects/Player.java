package com.example.towerdefence.objects;

import com.example.towerdefence.objects.tower.*;

public class Player {

    private String name;
    //default initialize with no money
    private int money;

    private int enemiesKilled;

    private int playTime;

    private int difficulty;

    //returns the class of the currently selected tower
    private Class currSelected;

    /**
     * initializes Player object which has 0 money and null name
     */
    public Player() {
        this.name = null;
        this.money = 0;
        this.difficulty = 0;
        this.playTime = 0;
        this.enemiesKilled = 0;
    }

    /**
     * returns the name of the player
     *
     * @return name of player
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the name of the player
     *
     * @param name new name of the player
     * @return 0 if name successfully changed and -1
     */
    public int setName(String name) {
        if (name == null || name.trim().length() == 0) {
            //empty name or whitespaces only
            return -1;
        }
        this.name = name;
        return 0;
    }

    /**
     * return the current amount of money that the player has
     *
     * @return player's money
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * set the amount of money held by the player
     *
     * @param money new money of money that player has
     * @return 0 if player money changed successfully, -1 if new money is negative
     */
    public int setMoney(int money) {
        if (money < 0) {
            //money cannot be negative
            return -1;
        }
        this.money = money;
        return 0;
    }

    /**
     * returns the current difficulty setting of the player
     *
     * @return difficulty setting of player
     */
    public int getDifficulty() {
        return this.difficulty;
    }


    /**
     * sets the difficulty setting of the player
     *
     * @param difficulty new difficulty setting of player
     * @return 0 if difficulty setting changed successfully
     */
    public int setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return 0;
    }

    /**
     * returns cost factor of the player that is based on the difficulty of the player
     *
     * @return cost factor to multiply the base cost of the towers by
     */
    public double getCostFactor() {
        if (difficulty == 3) {
            return 2;
        } else if (difficulty == 2) {
            return 1.5;
        } else if (difficulty == 1) {
            return 1;
        }
        throw new RuntimeException("invalid difficulty value");
    }

    /**
     * return the currently selected and paid for tower that is associated with the player
     *
     * @return currently selected tower
     */
    public Class getCurrSelected() {
        return this.currSelected;
    }

    /**
     * check if towerClass is null or an actual tower, null is to reset the value to nothing
     *
     * @param towerClass towerClass that is currently selected by player
     * @return 0 if tower class is successfully set, -1 if tower class is not successfully set
     */
    public int setCurrSelected(Class towerClass) {
        if (towerClass != null && !Tower.class.isAssignableFrom(towerClass)) {
            //this is not a tower class
            return -1;
        }
        this.currSelected = towerClass;
        return 0;
    }

    public int getPlayerCost(Tower tower) {
        return (int) (getCostFactor() * tower.getBasicCost());
    }

    public int getPlayerCost(Class towerClass) {
        if (!Tower.class.isAssignableFrom(towerClass)) {
            //if the class is not a subclass of tower
            return -1;
        }
        try {
            //need to create instance to use the instance method, cannot get the static attribute
            //directly from Class, get the class of int[] by instantiating one and .getClass()
            return (int) (this.getCostFactor() * (int) towerClass.getMethod("getBasicCost")
                    .invoke(Tower.createTower(towerClass, 0, 0)));
        } catch (Exception e) {
            throw new RuntimeException("getPlayerCost cannot getBasicCost method from tower class");
        }
    }

    public int getPlayTime() {
        return this.playTime;
    }

    public int setPlayTime(int time) {
        if (time < 0) {
            return -1;
        }
        this.playTime = time;
        return 0;
    }

    /**
     * increment play time by amount
     *
     * @param time amount to increment playtime by
     * @return 0 if successfully incremented when time >= 0, -1 otherwise
     */
    public int incrementPlayTime(int time) {
        if (time < 0) {
            return -1;
        }
        this.playTime += time;
        return 0;
    }

    public int getEnemiesKilled() {
        return this.enemiesKilled;
    }

    public int setEnemiesKilled(int enemiesKilled) {
        if (enemiesKilled < 0) {
            return -1;
        }
        this.enemiesKilled = enemiesKilled;
        return 0;
    }

    /**
     * increment enemiesKilled
     */
    public void enemyKilled() {
        this.enemiesKilled++;
    }
}
