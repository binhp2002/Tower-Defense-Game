package com.example.towerdefence;
import com.example.towerdefence.Controllers.*;
import com.example.towerdefence.objects.Monument;
import com.example.towerdefence.objects.Player;
import com.example.towerdefence.Controllers.StartUpPageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

public class GameApplication extends Application {

    private Stage window;
    private Scene gameScene;

    private Player player;
    private Monument monument;

    private HashMap<String, Object> controllerMap;

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.player = new Player();
        this.monument = new Monument();
        this.window = stage;
        this.controllerMap = new HashMap<>();

        //load scenes
        //start up screen
        FXMLLoader startUpPane = new FXMLLoader(getClass().getResource("/StartUpPage.fxml"));
        Parent startUpPaneLoader = startUpPane.load();
        Scene startUpScene = new Scene(startUpPaneLoader, 640, 480);

        //config screen scene
        FXMLLoader configScreenPane = new FXMLLoader(getClass()
                .getResource("/ConfigScreenPage.fxml"));
        Parent configScreenPaneLoader = configScreenPane.load();
        //BorderPane pane = new BorderPane();
        //pane.setCenter(addGridPane());
        Scene configScreenScene = new Scene(configScreenPaneLoader, 640, 480);

        //game screen scene
        FXMLLoader gameScreenPane = new FXMLLoader(getClass().getResource("/GameApp.fxml"));
        Parent gameScreenPaneLoader = gameScreenPane.load();
        Scene gameScreenScene = new Scene(gameScreenPaneLoader, 1100, 600);

        //win screen scene
        FXMLLoader winScreenPane = new FXMLLoader(getClass().getResource("/WinScreenPage.fxml"));
        Parent winScreenPaneLoader = winScreenPane.load();
        Scene winScreenScene = new Scene(winScreenPaneLoader, 1100, 600);

        //set next scenes for each scene
        //get the controller and then set the next page for the controller
        StartUpPageController startUpController = startUpPane.getController();
        startUpController.setNextScene(configScreenScene);
        this.controllerMap.put("StartUpPageController", startUpController);

        ConfigScreenController configScreenController = configScreenPane.getController();
        configScreenController.setNextScene(gameScreenScene);
        configScreenController.setPlayer(player);
        configScreenController.setMonument(monument);
        this.controllerMap.put("ConfigScreenController", configScreenController);

        GameScreenController gameScreenController = gameScreenPane.getController();
        gameScreenController.setPlayer(player);
        gameScreenController.setMonument(monument);
        gameScreenController.setNextScene(winScreenScene);
        this.controllerMap.put("GameScreenController", gameScreenController);

        WinScreenController winScreenController = winScreenPane.getController();
        gameScreenController.setPlayer(player);
        gameScreenController.setMonument(monument);
        winScreenController.setNextScene(startUpScene);
        //reset stage
        winScreenController.setNewStage(stage);
        this.controllerMap.put("WinScreenController", winScreenController);

        stage.setTitle("Tower Defense Game");
        stage.setScene(startUpScene);
        stage.requestFocus();
        stage.show();

    }

    /**
     * returns player object for inspection
     * @return game player object
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * returns monument object for inspection
     * @return game monument object
     */
    public Monument getMonument() {
        return this.monument;
    }

    public HashMap<String, Object> getControllerMap() {
        return this.controllerMap;
    }

    /**
     * Main method. Runs at launch
     * @param args Standard parameter
     */
    public static void main(String[] args) {
        launch();
    }
}