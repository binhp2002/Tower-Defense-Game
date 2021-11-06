package com.example.towerdefence.objects.tower;

public class TowerRow {
    private Tower[][] towerRow;

    /**
     * Constructor to create towerRow of specified size
     * @param numRows
     * @param numColumns
     */
    public TowerRow(int numRows, int numColumns) {
        this.towerRow = new Tower[numRows][numColumns];
    }

    /**
     * Method to insert tower into towerRow
     * @param row
     * @param column
     * @param addTower
     */
    public void insertTower(int row, int column, Tower addTower) {
        towerRow[row][column] = addTower;
    }
}