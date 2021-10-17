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
    private BasicTower basicTower;
    private SniperTower sniperTower;
    private MachineTower machineTower;

    private Scene nextScene;
    public Scene getNextScene() {
        return this.nextScene;
    }
    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

    public GameScreenController() {
        this.player = ConfigScreenController.getPlayer();
        this.basicTower = ConfigScreenController.getBasicTower();
        this.sniperTower = ConfigScreenController.getSniperTower();
        this.machineTower = ConfigScreenController.getMachineTower();
    }

    @FXML
    public void BasicTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(basicTower)) {
            player.setCurrSelected(BasicTower.class);
        }
    }

    @FXML
    public void SniperTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(sniperTower)) {
            player.setCurrSelected(SniperTower.class);
        }
    }

    @FXML
    public void MachineTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(machineTower)) {
            player.setCurrSelected(MachineTower.class);
        }
    }


}
