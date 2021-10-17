package com.example.towerdefence.GameScreenPage;
import com.example.towerdefence.ConfigScreenPage.ConfigScreenController;
import com.example.towerdefence.objects.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameScreenController {

    private Player player;
    private Monument monument;

    private Scene nextScene;

    public Scene getNextScene() {
        return this.nextScene;
    }

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

    @FXML
    public void BasicTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(BasicTower.class)) {
            player.setCurrSelected(BasicTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(BasicTower.class));
        }
    }

    @FXML
    public void SniperTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(SniperTower.class)) {
            player.setCurrSelected(SniperTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(SniperTower.class));
        }
    }

    @FXML
    public void MachineTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(MachineTower.class)) {
            player.setCurrSelected(MachineTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(MachineTower.class));
            System.out.println(player.getMoney());
        }
    }
}
