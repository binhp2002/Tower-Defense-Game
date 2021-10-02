package com.example.towerdefence;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class GameApplication extends Application {
    private static Text name;
    private static TextField entry;
    private static String input = "None";
    private static Text difficulty;
    private static String selection = "None";
    private static Text moneyT; //the money's title
    private static int money = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        BorderPane pane = new BorderPane();
        pane.setCenter(addGridPane());
        Scene scene = new Scene(pane, 640, 480);
        stage.setTitle("Game Configuration");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Show the game and player information buttons at the center of the scene.
     * @return the game and player information buttons.
     */
    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        entry = new TextField();
        entry.setPromptText("Enter your name here");
        grid.add(entry, 1, 1);

        name = new Text("Name: " + input);
        grid.add(name, 1, 0);

        Button enter = new Button("Enter");
        grid.add(enter, 2, 1);
        enter.setOnMouseClicked(e -> {
            input = entry.getText();
            if (input == null || input.equals(" ") || input.isEmpty()) {
                name.setText("Name: Name is not allow, try again");
            } else {
                name.setText("Name: " + input);
            }
        });

        difficulty = new Text("Difficulty: " + selection);
        grid.add(difficulty, 1, 3);

        moneyT = new Text("Money: " + money);
        grid.add(moneyT, 1, 4);

        Button easy = new Button("Easy");
        grid.add(easy, 2, 3);
        easy.setOnMouseClicked(e -> {
            money = 1000;
            selection = "Easy";
            difficulty.setText("Difficulty: " + selection);
            moneyT.setText("Money: " + money);
        });

        Button medium = new Button("Medium");
        grid.add(medium, 2, 4);
        medium.setOnMouseClicked(e -> {
            money = 500;
            selection = "Medium";
            difficulty.setText("Difficulty: " + selection);
            moneyT.setText("Money: " + money);
        });

        Button hard = new Button("Hard");
        grid.add(hard, 2, 5);
        hard.setOnMouseClicked(e -> {
            money = 100;
            selection = "Hard";
            difficulty.setText("Difficulty: " + selection);
            moneyT.setText("Money: " + money);
        });

        Button startGame = new Button("Start Game");
        grid.add(startGame, 3, 7);
        startGame.setOnMouseClicked(e -> {
            
        });

        return grid;
    }

    public static void main(String[] args) {
        launch();
    }
}