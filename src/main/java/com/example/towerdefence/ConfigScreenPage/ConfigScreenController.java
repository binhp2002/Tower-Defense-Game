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
import org.controlsfx.control.action.Action;

//import java.awt.*;

public class ConfigScreenController {
    Scene nextScene;
    private Player player;
    private Monument monument;

    @FXML
    Text namePrompt;

    @FXML
    Text difficultyPrompt;

    @FXML
    Text moneyPrompt;

    @FXML
    Text incompletePrompt;

    @FXML
    TextField entry;

    String input = "None";
    int difficultySelection = 0;

    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMonument(Monument monument) {
        this.monument = monument;
    }

    @FXML
    public void enterButton(ActionEvent e) {
        if (player.setName(entry.getText()) == -1) {
            namePrompt.setText("Name: Name is not allow, try again");
        } else {
            namePrompt.setText("Name: " + entry.getText());
        }
    }

    @FXML
    public void easyButton(ActionEvent e) {
        player.setMoney(1000);
        player.setDifficulty(1);
        difficultyPrompt.setText("Difficulty: " + 1);
        moneyPrompt.setText("Money: " + 1000);
        monument.setHealth(150);
    }

    @FXML
    public void mediumButton(ActionEvent e) {
        player.setMoney(500);
        player.setDifficulty(2);
        difficultyPrompt.setText("Difficulty: " + 2);
        moneyPrompt.setText("Money: " + 500);
        monument.setHealth(100);
    }

    @FXML
    public void hardButton(ActionEvent e) {
        player.setMoney(100);
        player.setDifficulty(3);
        difficultyPrompt.setText("Difficulty: " + 3);
        moneyPrompt.setText("Money: " + 100);
        monument.setHealth(50);
    }

    @FXML
    public void startGameButton(ActionEvent e) {
        if (player.getName() == null || player.getMoney() == 0) {
            //some of the player settings not selected
            incompletePrompt.setText("Please select a difficulty and name and try again");
        } else {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(this.nextScene);
            stage.setTitle("Game Screen");
        }
    }
}
