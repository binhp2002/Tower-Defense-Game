package com.example.towerdefence.objects.tower;

import javafx.scene.layout.*;

public class TowerRow {
    private Tower[][] towerRow;
    private int numRows;
    private int numColumns;

    /**
     * Constructor to create towerRow of specified size
     * @param numRows number of rows
     * @param numColumns number of columns
     */
    public TowerRow(int numRows, int numColumns) {
        this.towerRow = new Tower[numRows][numColumns];
        this.numRows = numRows;
        this.numColumns = numColumns;
    }

    /**
     * Constructor to create towerRow to model a particular GridPane (intended to be the
     * actual grid pane object)
     * @param gridPane
     */
    public TowerRow(GridPane gridPane) {
        //get the row and column count based on the grid pane object
        this(gridPane.getRowCount(), gridPane.getColumnCount());
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
     * @param insertRow row to insert tower to
     * @param insertColumn column to insert tower to
     * @param addTower tower to be added
     */
    public void insertTower(int insertRow, int insertColumn, Tower addTower) {
        //doing 0 indexing, so insertRow should be between 0 and numRows - 1 (inclusive)
        if (insertRow >= numRows || insertRow < 0) {
            throw new IllegalArgumentException("Error - row provided out of bounds");
        }

        if (insertColumn >= numColumns || insertColumn < 0) {
            throw new IllegalArgumentException("Error - column provided out of bounds");
        }

        towerRow[insertRow][insertColumn] = addTower;
    }

    /**
     * return the tower at specified row and column
     * @param row row to get tower from
     * @param column column to get tower from
     * @return Tower at that location (might be null)
     */
    public Tower getTower(int row, int column) {
        return this.towerRow[row][column];
    }

}