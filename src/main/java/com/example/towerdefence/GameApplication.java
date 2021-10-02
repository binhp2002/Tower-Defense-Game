package com.example.towerdefence;
import com.example.towerdefence.objects.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class GameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("hello-view.fxml"));
        Player player = new Player();
//        StackPane map = new StackPane();
        VBox map = new VBox();

//        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        Scene scene = new Scene(map, 1000, 600);
        stage.setTitle("Tower Defense Game");
        stage.setScene(scene);
        stage.requestFocus();
        stage.show();
        mapSetter(map, stage, player);
    }
    private void mapSetter(VBox map, Stage stage, Player player) {
        //rectangle initialiser
        Rectangle rectUpper = new Rectangle();
        Rectangle rectMid = new Rectangle(stage.getWidth(),180);
        Rectangle rectLower = new Rectangle(stage.getWidth(),210);
        Rectangle monument= new Rectangle(50,180);

        //Color setter
        rectUpper.setFill(Color.GREEN);
        rectLower.setFill(Color.GREEN);
        rectMid.setFill(Color.WHITE);
//        rectMid.setStyle("-fx-background-color: " + "Red");

//        pane.add(rectMid,0,(int)stage.getHeight()*7/20);




//        pane.add(rectLower, 0, (int)stage.getHeight()*13/20);
//        pane.add(monument, 0, (int)stage.getHeight()*7/20);
//
//        int money = player.getMoney();
        Label statusLabel = new Label("Welcome to the Color Game!");
        map.getChildren().add(rectUpper);

//        statusLabel.setStyle("-fx-background-color: white;");
//        pane.add(statusLabel, 0, 0);
    }

    public static void main(String[] args) {
        launch();
    }
}