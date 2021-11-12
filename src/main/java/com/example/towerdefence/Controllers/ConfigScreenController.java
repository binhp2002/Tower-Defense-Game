package com.example.towerdefence.Controllers;
import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.tower.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfigScreenController {
    private Scene nextScene;
    private Player player;
    private Monument monument;

    private Text namePrompt;
    private Text difficultyPrompt;
    private Text moneyPrompt;
    private Text incompletePrompt;
    private TextField entry;

    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMonument(Monument monument) {
        this.monument = monument;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Monument getMonument() {
        return this.monument;
    }


    @FXML
    public void enterButton(ActionEvent e) {
        if (player.setName(((TextField) ((Node) e.getSource())
                .getScene().lookup("#entry")).getText()) == -1) {
            ((Text) ((Node) e.getSource()).getScene().lookup("#namePrompt"))
                    .setText("Name: Name is not allow, try again");
            //namePrompt.setText("Name: Name is not allow, try again");
        } else {
            ((Text) ((Node) e.getSource()).getScene().lookup("#namePrompt"))
                    .setText("Name: " + ((TextField) ((Node) e.getSource())
                            .getScene().lookup("#entry")).getText());
            //namePrompt.setText("Name: " + entry.getText());
        }
    }

    @FXML
    public void easyButton(ActionEvent e) {
        player.setMoney(1000);
        player.setDifficulty(1);
        ((Text) ((Node) e.getSource()).getScene().lookup("#difficultyPrompt"))
                .setText("Difficulty: " + 1);
        ((Text) ((Node) e.getSource()).getScene().lookup("#moneyPrompt")).setText("Money: " + 1000);
        monument.setHealth(150);
    }

    @FXML
    public void mediumButton(ActionEvent e) {
        player.setMoney(500);
        player.setDifficulty(2);
        ((Text) ((Node) e.getSource()).getScene().lookup("#difficultyPrompt"))
                .setText("Difficulty: " + 2);
        ((Text) ((Node) e.getSource()).getScene().lookup("#moneyPrompt"))
                .setText("Money: " + 500);
        monument.setHealth(100);
    }

    @FXML
    public void hardButton(ActionEvent e) {
        player.setMoney(100);
        player.setDifficulty(3);
        ((Text) ((Node) e.getSource()).getScene().lookup("#difficultyPrompt"))
                .setText("Difficulty: " + 3);
        ((Text) ((Node) e.getSource()).getScene().lookup("#moneyPrompt"))
                .setText("Money: " + 100);
        monument.setHealth(50);
    }

    @FXML
    public void startGameButton(ActionEvent e) {
        if (player.getName() == null || player.getMoney() == 0) {
            //some of the player settings not selected
            ((Text) ((Node) e.getSource()).getScene().lookup("#incompletePrompt"))
                    .setText("Please select a difficulty and name and try again");
        } else {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //next scene is the game page, need to set the player health and money text
            ((Text) this.nextScene.lookup("#playerParameters")).setText("Money: "
                    + player.getMoney() + " Health: " + monument.getHealth());

            ((Text) this.nextScene.lookup("#BasicTowerName")).setText(BasicTower.NAME);
            ((Text) this.nextScene.lookup("#SniperTowerName")).setText(SniperTower.NAME);
            ((Text) this.nextScene.lookup("#MachineTowerName")).setText(MachineTower.NAME);

            ((TextArea) this.nextScene.lookup("#BasicTowerCost")).setText("Cost: "
                    + player.getPlayerCost(BasicTower.class));
            ((TextArea) this.nextScene.lookup("#SniperTowerCost")).setText("Cost: "
                    + player.getPlayerCost(SniperTower.class));
            ((TextArea) this.nextScene.lookup("#MachineTowerCost")).setText("Cost: "
                    + player.getPlayerCost(MachineTower.class));

            ((TextArea) this.nextScene.lookup("#BasicTowerDescription"))
                    .setText(BasicTower.DESCRIPTION);
            ((TextArea) this.nextScene.lookup("#SniperTowerDescription"))
                    .setText(SniperTower.DESCRIPTION);
            ((TextArea) this.nextScene.lookup("#MachineTowerDescription"))
                    .setText(MachineTower.DESCRIPTION);


            GridPane topTowerRow = (GridPane) this.nextScene.lookup("#topTowerRow");

            for (int i = 0; i < topTowerRow.getColumnCount(); i++) {
                for (int j = 0; j < topTowerRow.getRowCount(); j++) {
                    ImageView tower = new ImageView();
                    GridPane.setRowIndex(tower, j);
                    GridPane.setColumnIndex(tower, i);
                    topTowerRow.getChildren().add(tower);
                }
            }

            GridPane bottomTowerRow = (GridPane) this.nextScene.lookup("#bottomTowerRow");

            for (int i = 0; i < bottomTowerRow.getColumnCount(); i++) {
                for (int j = 0; j < bottomTowerRow.getRowCount(); j++) {
                    ImageView tower = new ImageView();
                    GridPane.setRowIndex(tower, j);
                    GridPane.setColumnIndex(tower, i);
                    bottomTowerRow.getChildren().add(tower);
                }
            }

            stage.setScene(this.nextScene);
            stage.setTitle("Tower Defense Game");

        }
    }
}
