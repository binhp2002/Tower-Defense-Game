package com.example.towerdefence.objects.tower;

public class TowerRow {
    private Tower[][] towerRow;
    private int numRows;
    private int numColumns;

    /**
     * Constructor to create towerRow of specified size
     * @param numRows
     * @param numColumns
     */
    public TowerRow(int numRows, int numColumns) {
        this.towerRow = new Tower[numRows][numColumns];
        this.numRows = numRows;
        this.numColumns = numColumns;
    }

    /**
     * gets number of rows of TowerRow
     * @return number of rows
     */
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * gets number of columns of TowerRow
     * @return number of columns
     */
    public int getNumColumns() {
        return this.numColumns;
    }

    /**
     * Method to insert tower into towerRow
     * @param insertRow
     * @param insertColumn
     * @param addTower
     */
    public void insertTower(int insertRow, int insertColumn, Tower addTower) {
        if (insertRow > numRows || insertColumn <= 0) {
            throw new IllegalArgumentException("Error - row provided out of bounds");
        }

        if (insertColumn > numColumns || insertColumn <= 0) {
            throw new IllegalArgumentException("Error - column provided out of bounds");
        }

        towerRow[insertRow][insertColumn] = addTower;
    }
}