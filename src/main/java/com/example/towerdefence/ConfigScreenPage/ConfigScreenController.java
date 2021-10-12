package com.example.towerdefence.ConfigScreenPage;
import com.example.towerdefence.objects.Monument;
import com.example.towerdefence.objects.Player;
import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfigScreenController {
    Scene nextScene;
    private Player player;
    private Monument monument;

    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

    @FXML
    public GridPane addGridPane() {

        Text namePrompt;
        Text difficultyPrompt;
        Text moneyPrompt;
        Text incompletePrompt;
        TextField entry;
        String input = "None";
        int difficultySelection = 0;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        entry = new TextField();
        entry.setPromptText("Enter your name here");
        entry.setId("entry");
        grid.add(entry, 1, 1);

        namePrompt = new Text("Name: " + input);

        namePrompt.setId("namePrompt");

        grid.add(namePrompt, 1, 0);

        Button enter = new Button("Enter");
        enter.setId("enter");
        grid.add(enter, 2, 1);
        enter.setOnMouseClicked(e -> {
            if (player.setName(entry.getText()) == -1) {
                namePrompt.setText("Name: Name is not allow, try again");
            } else {
                namePrompt.setText("Name: " + entry.getText());
            }
        });

        difficultyPrompt = new Text("Difficulty: " + difficultySelection);
        difficultyPrompt.setId("difficultyPrompt");
        grid.add(difficultyPrompt, 1, 3);

        moneyPrompt = new Text("Money: " + 0);
        moneyPrompt.setId("moneyPrompt");
        grid.add(moneyPrompt, 1, 4);

        Button easy = new Button("Easy");
        easy.setId("easy");
        grid.add(easy, 2, 3);
        easy.setOnMouseClicked(e -> {
            player.setMoney(1000);
            player.setDifficulty(1);
            difficultyPrompt.setText("Difficulty: " + 1);
            moneyPrompt.setText("Money: " + 1000);
            monument.setHealth(150);
        });

        Button medium = new Button("Medium");
        medium.setId("medium");
        grid.add(medium, 2, 4);
        medium.setOnMouseClicked(e -> {
            player.setMoney(500);
            player.setDifficulty(2);
            difficultyPrompt.setText("Difficulty: " + 2);
            moneyPrompt.setText("Money: " + 500);
            monument.setHealth(100);
        });

        Button hard = new Button("Hard");
        hard.setId("hard");
        grid.add(hard, 2, 5);
        hard.setOnMouseClicked(e -> {
            player.setMoney(100);
            player.setDifficulty(3);
            difficultyPrompt.setText("Difficulty: " + 3);
            moneyPrompt.setText("Money: " + 100);
            monument.setHealth(50);
        });

        Button startGame = new Button("Start Game");
        startGame.setId("startGame");
        grid.add(startGame, 3, 7);
        incompletePrompt = new Text();
        incompletePrompt.setId("incompletePrompt");
        grid.add(incompletePrompt, 4, 7);
        startGame.setOnMouseClicked(e -> {
            if (player.getName() == null || player.getMoney() == 0) {
                //some of the player settings not selected
                incompletePrompt.setText("Please select a difficulty and name and try again");
            } else {
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.setScene(this.nextScene);
                stage.setTitle("Game Screen");
            }
        });

        return grid;
    }
}
