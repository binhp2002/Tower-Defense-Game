package com.example.towerdefence;
import com.example.towerdefence.objects.Monument;
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
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Stack;

public class GameApplication extends Application {

    private Player player;
    private Monument monument;

    @Override
    public void start(Stage stage) throws IOException {
        this.player = new Player();

        //use VBox layout for the initial start screen with a button and text
        VBox startVBox = new VBox();

        Label startGamePrompt = new Label();

        startGamePrompt.setText("Click button to start playing...");

        Button startGameButton = new Button();

        //set text for the game button
        startGameButton.setText("Start Game!");

        startGameButton.setOnAction(event -> {
            //call config screen event

        });

        //add the start game prompt on top of the start game button
        startVBox.getChildren().add(startGamePrompt);
        startVBox.getChildren().add(startGameButton);

        //Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        Scene initialScreenScene = new Scene(startVBox, 640, 480);
        stage.setTitle("Tower Defense Game");
        stage.setScene(initialScreenScene);
        stage.requestFocus();
        stage.show();
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
        //rectMid.setStyle("-fx-background-color: " + "Red");

        //pane.add(rectMid,0,(int)stage.getHeight()*7/20);




        //pane.add(rectLower, 0, (int)stage.getHeight()*13/20);
        //pane.add(monument, 0, (int)stage.getHeight()*7/20);

        //int money = player.getMoney();
        Label statusLabel = new Label("Welcome to the Color Game!");
        map.getChildren().add(rectUpper);

        //statusLabel.setStyle("-fx-background-color: white;");
        //pane.add(statusLabel, 0, 0);
    }

    public static void main(String[] args) {
        launch();
    }
}