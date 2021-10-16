package com.example.towerdefence.GameScreenPage;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;


public class GameScreenController {

    private Scene nextScene;

    public Scene getNextScene() {
        return this.nextScene;
    }

    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

    @FXML
    public void mouseEntered(MouseEvent e) throws FileNotFoundException {
        GridPane node = (GridPane) e.getSource();
        Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());

        double cellWidth = boundsInScene.getWidth() / node.getColumnCount();
        double cellHeight = boundsInScene.getHeight() / node.getRowCount();

        double clickX = e.getX();
        double clickY = e.getY();

        int colIndex = (int) (clickX / cellWidth);
        int rowIndex = (int) (clickY / cellHeight);

        ImageView cell = (ImageView) getNodeByCoordinate(rowIndex, colIndex, node);
        System.out.println(((ImageView) node.getScene().lookup("#testMonumentImage")));
        cell.setImage(new Image("file:./target/classes/images/SniperTower.png"));
        cell.setFitHeight(cellHeight);
        cell.setFitWidth(cellWidth);

    }

    Node getNodeByCoordinate(Integer row, Integer column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node != null && GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node).equals(row) &&
                    GridPane.getColumnIndex(node).equals(column)){
                return node;
            }
        }
        return null;
    }
}
