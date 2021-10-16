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


    Text namePrompt;
    Text difficultyPrompt;
    Text moneyPrompt;
    Text incompletePrompt;
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
        if (player.setName(((TextField) ((Node) e.getSource()).getScene().lookup("#entry")).getText()) == -1) {
            ((Text) ((Node) e.getSource()).getScene().lookup("#namePrompt")).setText("Name: Name is not allow, try again");
            //namePrompt.setText("Name: Name is not allow, try again");
        } else {
            ((Text) ((Node) e.getSource()).getScene().lookup("#namePrompt")).setText("Name: " + ((TextField) ((Node) e.getSource()).getScene().lookup("#entry")).getText());
            //namePrompt.setText("Name: " + entry.getText());
        }
    }

    @FXML
    public void easyButton(ActionEvent e) {
        player.setMoney(1000);
        player.setDifficulty(1);
        ((Text) ((Node) e.getSource()).getScene().lookup("#difficultyPrompt")).setText("Difficulty: " + 1);
        ((Text) ((Node) e.getSource()).getScene().lookup("#moneyPrompt")).setText("Money: " + 1000);
        monument.setHealth(150);
    }

    @FXML
    public void mediumButton(ActionEvent e) {
        player.setMoney(500);
        player.setDifficulty(2);
        ((Text) ((Node) e.getSource()).getScene().lookup("#difficultyPrompt")).setText("Difficulty: " + 2);
        ((Text) ((Node) e.getSource()).getScene().lookup("#moneyPrompt")).setText("Money: " + 500);
        monument.setHealth(100);
    }

    @FXML
    public void hardButton(ActionEvent e) {
        player.setMoney(100);
        player.setDifficulty(3);
        ((Text) ((Node) e.getSource()).getScene().lookup("#difficultyPrompt")).setText("Difficulty: " + 3);
        ((Text) ((Node) e.getSource()).getScene().lookup("#moneyPrompt")).setText("Money: " + 100);
        monument.setHealth(50);
    }

    @FXML
    public void startGameButton(ActionEvent e) {
        if (player.getName() == null || player.getMoney() == 0) {
            //some of the player settings not selected
            ((Text) ((Node) e.getSource()).getScene().lookup("#incompletePrompt")).setText("Please select a difficulty and name and try again");
        } else {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //next scene is the game page, need to set the player health and money text
            ((Text) this.nextScene.lookup("#playerParameters")).setText("Money: " + player.getMoney() +
                    " Health: " + monument.getHealth());
            stage.setScene(this.nextScene);
            stage.setTitle("Tower Defense Game");
        }
    }
}
