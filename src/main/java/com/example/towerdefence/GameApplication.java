package com.example.towerdefence;
import com.example.towerdefence.ConfigScreenPage.ConfigScreenController;
import com.example.towerdefence.objects.Monument;
import com.example.towerdefence.objects.Player;
import com.example.towerdefence.StartUpPage.StartUpPageController;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class GameApplication extends Application {

    private Stage window;
    private Scene gameScene;

    private Player player;
    private Monument monument;

    @Override
    public void start(Stage stage) throws IOException {
        this.player = new Player();
        this.monument = new Monument();
        this.window = stage;

        //load scenes
        //start up screen
        FXMLLoader startUpPane = new FXMLLoader(getClass().getResource("/StartUpPage.fxml"));
        Parent startUpPaneLoader = startUpPane.load();
        Scene startUpScene = new Scene(startUpPaneLoader, 640, 480);

        //config screen scene
        FXMLLoader configScreenPane = new FXMLLoader(getClass().getResource("/ConfigScreenPage.fxml"));
        Parent configScreenPaneLoader = configScreenPane.load();
        //BorderPane pane = new BorderPane();
        //pane.setCenter(addGridPane());
        Scene configScreenScene = new Scene(configScreenPaneLoader, 640, 480);

        //game screen scene
        FXMLLoader gameScreenPane = new FXMLLoader(getClass().getResource("/GameApp.fxml"));
        Parent gameScreenPaneLoader = gameScreenPane.load();
        Scene gameScreenScene = new Scene(gameScreenPaneLoader, 1080, 720);


        //set next scenes for each scene
        //get the controller and then set the next page for the controller
        StartUpPageController startUpController = startUpPane.getController();
        startUpController.setNextScene(configScreenScene);

        ConfigScreenController configScreenController = configScreenPane.getController();
        configScreenController.setNextScene(gameScreenScene);
        configScreenController.setPlayer(player);
        configScreenController.setMonument(monument);

        stage.setTitle("Tower Defense Game");
        stage.setScene(startUpScene);
        stage.requestFocus();
        stage.show();

    }

    /**
     * Main method. Runs at launch
     * @param args Standard parameter
     */
    public static void main(String[] args) {
        launch();
    }
}