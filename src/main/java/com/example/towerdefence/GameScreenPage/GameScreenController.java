package com.example.towerdefence.GameScreenPage;
import javafx.css.Style;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;


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

        ImageView cell = (ImageView) getNodeByCoordinate(rowIndex, colIndex, node);
        Image image = new Image(new File("images/monument.png").toURI().toString());
        cell.setImage(image);
        //cell.setStyle("-fx-background-color:#FF0000");
        //cell.setText("touch");
        System.out.println(cell.getImage());
        System.out.println(getNodeByCoordinate(rowIndex, colIndex, node).getClass());


        System.out.println(rowIndex + " " + colIndex);
    }

    Node getNodeByCoordinate(Integer row, Integer column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node != null && GridPane.getRowIndex(node).equals(row) &&
                    GridPane.getColumnIndex(node).equals(column)){
                return node;
            }
        }
        return null;
    }
}
