package com.example.towerdefence.GameScreenPage;
import javafx.css.Style;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class GameScreenController {

    private Scene nextScene;

    public Scene getNextScene() {
        return this.nextScene;
    }

    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

    public void mouseEntered(MouseEvent e) {
        GridPane node = (GridPane) e.getSource();
        Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());

        double cellWidth = boundsInScene.getWidth() / node.getColumnCount();
        double cellHeight = boundsInScene.getHeight() / node.getRowCount();

        double clickX = e.getX();
        double clickY = e.getY();

        int colIndex = (int) (clickX / cellWidth);
        int rowIndex = (int) (clickY / cellHeight);

        //System.out.println(rowIndex + " " + colIndex);
    }
}
