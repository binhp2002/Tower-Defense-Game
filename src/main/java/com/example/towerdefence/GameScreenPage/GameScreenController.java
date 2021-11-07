package com.example.towerdefence.GameScreenPage;
import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.enemy.*;
import com.example.towerdefence.objects.tower.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.util.*;

import java.util.*;
import java.util.concurrent.*;


public class GameScreenController {

    private Player player;
    private Monument monument;

    private Scene nextScene;

    private ArrayList<Integer> currWaveAnimationCode;

    private HashMap<GridPane, TowerRow> gameTowerRow = new HashMap<GridPane, TowerRow>();

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
        if (player.getCurrSelected() == null
                && player.getMoney() >= player.getPlayerCost(BasicTower.class)) {
            player.setCurrSelected(BasicTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(BasicTower.class));
            ((Text) ((Node) e.getSource()).getScene().lookup("#playerParameters"))
                    .setText("Money: " + player.getMoney() + " Health: " + monument.getHealth());
        }
    }

    @FXML
    public void sniperTowerPurchaseButton(ActionEvent e) {
        if (player.getCurrSelected() == null
                && player.getMoney() >= player.getPlayerCost(SniperTower.class)) {
            player.setCurrSelected(SniperTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(SniperTower.class));
            ((Text) ((Node) e.getSource()).getScene().lookup("#playerParameters"))
                    .setText("Money: " + player.getMoney() + " Health: " + monument.getHealth());
        }
    }

    @FXML
    public void machineTowerPurchaseButton(ActionEvent e) {
        if (player.getCurrSelected() == null
                && player.getMoney() >= player.getPlayerCost(MachineTower.class)) {
            player.setCurrSelected(MachineTower.class);
            player.setMoney(player.getMoney() - player.getPlayerCost(MachineTower.class));
            ((Text) ((Node) e.getSource()).getScene().lookup("#playerParameters"))
                    .setText("Money: " + player.getMoney() + " Health: " + monument.getHealth());
        }
    }

    /**
     * logic for the while loop that occurs when an enemy wave is in progress
     * @param enemyWave EnemyWave object representing the enemies in the current wave
     * @param enemyImageViewHashMap mapping from enemies to their respective ImageView objects in gamePath
     * @param currScene current Scene
     * @return animationIP ArrayList used to track if the wave is in progress
     */
    public ArrayList<Integer> gameMovementLoop(EnemyWave enemyWave, HashMap<Enemy, ImageView> enemyImageViewHashMap,
                                               Scene currScene) {
        //store whether the animation is in progress, empty if not, one element if there is
        ArrayList<Integer> animationIP = new ArrayList<>();

        animationIP.add(1);

        AnimationTimer animation = new AnimationTimer() {
            private double prev = 0.0;

            private final double frameTime = Math.pow(10, 7);

            @Override
            public void handle(long now) {
                if (now - prev < frameTime) {
                    return;
                }
                this.prev = now;

                //get the enemies that have reached the end
                List<Enemy> enemiesReached = enemyWave.moveEnemiesForward();

                for (Enemy enemy: enemiesReached) {
                    //store the image view for the enemy then remove it so no longer seen
                    ImageView enemyImageView = enemyImageViewHashMap.get(enemy);

                    //remove them from the HashMap so they aren't redrawn
                    enemyImageViewHashMap.remove(enemy);

                    //remove ImageView from gamePath
                    Pane gamePath = (Pane) currScene.lookup("#gamePath");
                    gamePath.getChildren().remove(enemyImageView);

                    //enemies doing damage to monument
                    monument.setHealth(monument.getHealth() - enemy.getDamage());
                    if (monument.getHealth() <= 0) {
                        //game over
                        GameScreenController.gameOver((StackPane) currScene
                                .lookup("#gameOverPane"));

                        if (animationIP != null && animationIP.size() == 1) {
                            //might have race condition where another handle call comes in while
                            //processing previous call
                            animationIP.remove(0);
                        }

                        this.stop();
                    }
                }

                for (Enemy enemy: enemyWave.getEnemies()) {
                    if (!enemyImageViewHashMap.containsKey(enemy)) {
                        throw new RuntimeException("enemy not in enemyWave.getEnemies, check if all killed" +
                                "enemies have been removed from enemyImageViewHashMap");
                    }

                    //the remaining enemies that have not been removed yet
                    updateEnemyImage(enemy, enemyImageViewHashMap.get(enemy));
                }

                //update player parameters
                ((Text) currScene.lookup("#playerParameters")).setText("Money: "
                        + player.getMoney() + " Health: " + monument.getHealth());
                if (enemyWave.getEnemies().isEmpty()) {
                    //current wave is over when there are no more enemies
                    //clear remaining enemies
                    ((Pane) currScene.lookup("#gamePath")).getChildren().clear();
                    if (animationIP != null && animationIP.size() == 1) {
                        //might have race condition where another handle call comes in while
                        //processing previous call
                        animationIP.remove(0);
                    }

                    this.stop();
                }
            }
        };

        animation.start();

        return animationIP;
    }

    /**
     * controller for start combat button, initializes the enemy wave and then calls game movement loop
     * @param actionEvent event object for start combat button click
     */
    @FXML
    public void startCombatButton(ActionEvent actionEvent) {
        if (this.currWaveAnimationCode != null && this.currWaveAnimationCode.size() != 0) {
            //there is a current wave ongoing, terminate
            return;
        }

        EnemyWave enemyWave = new EnemyWave();

        Scene currScene = ((Node) actionEvent.getSource()).getScene();

        Pane gamePath = (Pane) currScene.lookup("#gamePath");

        //creates associated enemies
        HashMap<Enemy,ImageView> enemyImageViewHashMap = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            enemyWave.addEnemy(BasicEnemy.class, (int) gamePath.getWidth(), i * 20);
        }
        for (int i = 5; i < 10; i++) {
            enemyWave.addEnemy(TankEnemy.class, (int) gamePath.getWidth(), i * 20);
        }

        for (Enemy enemy: enemyWave.getEnemies()) {
            //add the enemy to the game path and then associate the ImageView of the enemy with the enemy
            enemyImageViewHashMap.put(enemy, createEnemyImage(enemy, currScene));
        }

        this.currWaveAnimationCode = gameMovementLoop(enemyWave, enemyImageViewHashMap, currScene);
    }

    /**
     * creates the enemy image and inserts it to the scene
     * @param enemy Enemy object
     * @param scene current scene
     * @return ImageView object created for the Enemy object in the scene
     */
    public ImageView createEnemyImage(Enemy enemy, Scene scene) {
        //get the stack pane to add the elements to it
        Pane gamePath = (Pane) scene.lookup("#gamePath");
        ImageView enemyImageView = new ImageView(enemy.getImagePath());
        //enemy is a 10x10 image
        enemyImageView.setFitHeight(20);
        enemyImageView.setFitWidth(20);
        enemyImageView.setLayoutX(enemy.getRelativeLocation()[0]);
        enemyImageView.setLayoutY(enemy.getRelativeLocation()[1]);
        gamePath.getChildren().add(enemyImageView);
        //returns the ImageView associated with this enemy
        return enemyImageView;
    }


    /**
     * updates the enemy image view location for the enemy
     * @param enemy enemy that we are updating the image of
     * @param enemyImageView the ImageView for that enemy (move this ImageView position)
     */
    public void updateEnemyImage(Enemy enemy, ImageView enemyImageView) {
        enemyImageView.setLayoutX(enemy.getRelativeLocation()[0]);
        enemyImageView.setLayoutY(enemy.getRelativeLocation()[1]);
    }

    @FXML
    public void placeTower(MouseEvent e) {

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

            //get the absolute location of the tower by looking at the index and then taking the center
            //for that index
            int towerX = (int) (node.getLayoutX() + (colIndex + 0.5) * cellWidth);
            int towerY = (int) (node.getLayoutY() + (rowIndex + 0.5) * cellHeight);

            Tower tower;
            try {
                //(new int[]{}).getClass() used to get class of int[]
                tower = (Tower) this.player.getCurrSelected().getConstructor((new int[]{}).getClass())
                        .newInstance(new int[] {towerX, towerY});
            } catch (Exception exception) {
                System.out.println(exception);
                throw new RuntimeException("No image path method found for tower");
            }

            if (!gameTowerRow.containsKey(node)) {
                //create the towerRow now in gameTowerRow since not inside
                gameTowerRow.put(node, new TowerRow(node));
            }

            //get the appropriate tower row for the one that was just click
            TowerRow towerRow = gameTowerRow.get(node);
            //insert the tower to that tower row
            towerRow.insertTower(rowIndex, colIndex, tower);

            ImageView cell = (ImageView) getNodeByCoordinate(rowIndex, colIndex, node);

            cell.setImage(new Image(tower.getImagePath()));

            cell.setFitHeight(cellHeight);
            cell.setFitWidth(cellWidth);
            //reset the tower selected
            this.player.setCurrSelected(null);
        }
        //do nothing if no tower is currently selected by player

    }

    public Node getNodeByCoordinate(Integer row, Integer column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node != null && GridPane.getRowIndex(node) != null
                    && GridPane.getRowIndex(node).equals(row)
                    && GridPane.getColumnIndex(node).equals(column)) {
                return node;
            }
        }
        return null;
    }

    public static void gameOver(StackPane gameOverPane) {
        gameOverPane.setVisible(true);
        gameOverPane.setStyle("-fx-background-color: transparent;");
    }
}