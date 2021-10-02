package com.example.towerdefence;
import com.example.towerdefence.objects.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class GameApplication extends Application {

    Stage window;

    Player player;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("hello-view.fxml"));
        intialiseGameScreen();
    }
    private void intialiseGameScreen() throws FileNotFoundException {
        this.player = new Player();
        //StackPane map = new StackPane();
        HBox descriptionGrid = new HBox();
        HBox topLane = new HBox();
        HBox midLane = new HBox();
        HBox bottomLane = new HBox();
        VBox map = new VBox();
        map.getChildren().add(descriptionGrid);
        map.getChildren().add(topLane);
        map.getChildren().add(midLane);
        map.getChildren().add(bottomLane);
        Scene gameMapScene = new Scene(map, 1000, 600);
        gameMapScene.setFill(Color.WHITE);
        mapSetter(map, gameMapScene, descriptionGrid, topLane, midLane, bottomLane);
        //Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        window.setTitle("Tower Defense Game");
        window.setScene(gameMapScene);
        window.requestFocus();
        window.show();
    }
    private void mapSetter(VBox map, Scene scene, HBox descriptionGrid, HBox topLane, HBox midLane, HBox bottomLane) throws FileNotFoundException {
        //Rectangle initialiser
        Rectangle strip = new Rectangle(scene.getWidth(), 30);
        Rectangle monument= new Rectangle(70,180);

        //Action
        monument.setFill(Color.WHITE);
        descriptionGrid.getChildren().add(strip);
        towerFiller(topLane);

        //Add monument image
        FileInputStream inputstream = new FileInputStream("./src/main/resources/com/example/towerdefence/Images/monument.png");
        Image img = new Image(inputstream);
        ImageView imgView = new ImageView(img);
        StackPane monStack = new StackPane();
        imgView.setFitHeight(150);
        imgView.setFitWidth(130);
        monStack.getChildren().add(imgView);
        midLane.getChildren().add(monStack);

        //
        enemyPositionGridSetter(midLane);
        towerFiller(bottomLane);

    }

    /**
     * Placeholder creator for the towers the user will insert
     * @param lane the lane where the towers will be inserted at
     */
    private void towerFiller(HBox lane) {
        int cnt = 50;
        while(cnt<=1000) {
            Rectangle rect = new Rectangle(50, 180);
            rect.setStroke(Color.WHITE);
            rect.setFill(Color.GREEN);
            StackPane tower = new StackPane();
            tower.getChildren().addAll(rect, new Label("Tower"));
            lane.getChildren().add(tower);
            cnt+=50;
        }
    }
    private void enemyPositionGridSetter(HBox lane) {
        for(int i=0; i*20<=1000; i++){
            StackPane enemyPosition = new StackPane();
            Rectangle tile = new Rectangle(20,180);
            tile.setFill(Color.WHITE);
            tile.setStroke(Color.BLACK);
            tile.setStrokeWidth(1);
            enemyPosition.getChildren().add(tile);
            lane.getChildren().add(enemyPosition);
        }
    }

    private void displayGameParameters(HBox descriptionGrid) {
//        descriptionGrid.

    }


    public static void main(String[] args) {
        launch();
    }
}