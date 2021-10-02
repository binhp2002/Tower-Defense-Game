package com.example.towerdefence.objects;

public class Player {

    private String name = null;
    //default initialize with no money
    private int money = 0;

    private int difficulty = 0;

    /**
     * initializes Player object which has 0 money and null name
     */
    public Player() {

    }

    /**
     * returns the name of the player
     * @return name of player
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the name of the player
     * @param name new name of the player
     * @return 0 if name successfully changed and -1
     */
    public int setName(String name) {
        if (name == null || name.strip().length() == 0) {
            //empty name or whitespaces only
            return -1;
        }
        this.name = name;
        return 0;
    }

    /**
     * return the current amount of money that the player has
     * @return player's money
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * set the amount of money held by the player
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
     * @return difficulty setting of player
     */
    public int getDifficulty() {
        return this.difficulty;
    }


    /**
     * sets the difficulty setting of the player
     * @param difficulty new difficulty setting of player
     * @return 0 if difficulty setting changed successfully
     */
    public int setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return 0;
    }

}
