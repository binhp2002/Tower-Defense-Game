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
import java.io.IOException;
import java.util.Stack;

import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class GameApplication extends Application {
    //private static Text name, difficulty, moneyT;
    //private static TextField entry;
    //private static String input = "None";
    //private static Text difficulty;
    //private static String selection = "None";
    //private static Text moneyT; //the money's title
    //private static int money = 0;

    Stage window;
    Scene gameScene;

    Player player;
    Monument monument;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("hello-view.fxml"));
        this.player = new Player();
        this.window = stage;
        //Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        BorderPane pane = new BorderPane();
        pane.setCenter(addGridPane());
        Scene scene = new Scene(pane, 640, 480);
        stage.setTitle("Game Configuration");
        
        stage.setScene(scene);

        stage.show();
        //mapSetter(map, stage, this.player);

        initializeConfigScreen();
    }

    public void initializeConfigScreen() {
        //addGridPane();
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

    /**
     * Show the game and player information buttons at the center of the scene.
     * @return the game and player information buttons.
     */
    public GridPane addGridPane() {
        Text namePrompt, difficultyPrompt, moneyPrompt;
        TextField entry;
        String input = "None";
        int difficultySelection = 0;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        entry = new TextField();
        entry.setPromptText("Enter your name here");
        grid.add(entry, 1, 1);

        namePrompt = new Text("Name: " + input);
        grid.add(namePrompt, 1, 0);

        Button enter = new Button("Enter");
        grid.add(enter, 2, 1);
        enter.setOnMouseClicked(e -> {
            if (player.setName(entry.getText()) == -1) {
                namePrompt.setText("Name: Name is not allow, try again");
            } else {
                namePrompt.setText("Name: " + entry.getText());
            }
        });

        difficultyPrompt = new Text("Difficulty: " + difficultySelection);
        grid.add(difficultyPrompt, 1, 3);

        moneyPrompt = new Text("Money: " + 0);
        grid.add(moneyPrompt, 1, 4);

        Button easy = new Button("Easy");
        grid.add(easy, 2, 3);
        easy.setOnMouseClicked(e -> {
            player.setMoney(1000);
            player.setDifficulty(1);
            difficultyPrompt.setText("Difficulty: " + 1);
            moneyPrompt.setText("Money: " + 1000);
        });

        Button medium = new Button("Medium");
        grid.add(medium, 2, 4);
        medium.setOnMouseClicked(e -> {
            player.setMoney(500);
            player.setDifficulty(2);
            difficultyPrompt.setText("Difficulty: " + 2);
            moneyPrompt.setText("Money: " + 500);
        });

        Button hard = new Button("Hard");
        grid.add(hard, 2, 5);
        hard.setOnMouseClicked(e -> {
            player.setMoney(100);
            player.setDifficulty(3);
            difficultyPrompt.setText("Difficulty: " + 3);
            moneyPrompt.setText("Money: " + 100);
        });

        Button startGame = new Button("Start Game");
        grid.add(startGame, 3, 7);
        startGame.setOnMouseClicked(e -> {
            window.setScene(gameScene);
        });

        return grid;
    }

    public static void main(String[] args) {
        launch();
    }
}