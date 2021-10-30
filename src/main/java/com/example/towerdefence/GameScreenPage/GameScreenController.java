package com.example.towerdefence.GameScreenPage;
import com.example.towerdefence.objects.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;


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

    //Adding Images to GameScreen

    @FXML
    public void basicTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(BasicTower.class)) {
            player.setCurrSelected(BasicTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(BasicTower.class));
            ((Text) ((Node) e.getSource()).getScene().lookup("#playerParameters"))
                    .setText("Money: " + player.getMoney() + " Health: " + monument.getHealth());
        }
    }

    @FXML
    public void sniperTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(SniperTower.class)) {
            player.setCurrSelected(SniperTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(SniperTower.class));
            ((Text) ((Node) e.getSource()).getScene().lookup("#playerParameters"))
                    .setText("Money: " + player.getMoney() + " Health: " + monument.getHealth());
        }
    }

    @FXML
    public void machineTowerPurchaseButton(ActionEvent e) {
        if (player.getMoney() >= player.getPlayerCost(MachineTower.class)) {
            player.setCurrSelected(MachineTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(MachineTower.class));
            ((Text) ((Node) e.getSource()).getScene().lookup("#playerParameters"))
                    .setText("Money: " + player.getMoney() + " Health: " + monument.getHealth());
        }
    }


    //Time Loop
    Timeline enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(5),
            new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //move enemy
        }
    }));
    enemyMovementLoop.setCycleCount(Timeline.INDEFINITE);
    enemyMovementLoop.play();

    @FXML
    public void startGameButton(ActionEvent e) {
        Enemy [] gameEnemy = new Enemy[12];
        for (int i = 0; i < 12; i ++) {
            gameEnemy[i] = new Enemy();
        }
    }

    @FXML
    public void mouseEntered(MouseEvent e) {

        if (this.player.getCurrSelected() != null) {
            //if there is a tower selected by the player
            GridPane node = (GridPane) e.getSource();
            Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());

            double cellWidth = boundsInScene.getWidth() / node.getColumnCount();
            double cellHeight = boundsInScene.getHeight() / node.getRowCount();

            double clickX = e.getX();
            double clickY = e.getY();

            int colIndex = (int) (clickX / cellWidth);
            int rowIndex = (int) (clickY / cellHeight);

            ImageView cell = (ImageView) getNodeByCoordinate(rowIndex, colIndex, node);
            try {
                cell.setImage(new Image(((Tower) this.player.getCurrSelected().getConstructor()
                        .newInstance()).getImagePath()));
            } catch (Exception exception) {
                throw new RuntimeException("No image path method found for tower");
            }
            cell.setFitHeight(cellHeight);
            cell.setFitWidth(cellWidth);
            //reset the tower selected
            this.player.setCurrSelected(null);
        }
        //do nothing if no tower is currently selected by player

    }

    Node getNodeByCoordinate(Integer row, Integer column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node != null && GridPane.getRowIndex(node) != null
                    && GridPane.getRowIndex(node).equals(row)
                    && GridPane.getColumnIndex(node).equals(column)) {
                return node;
            }
        }
        return null;
    }
}