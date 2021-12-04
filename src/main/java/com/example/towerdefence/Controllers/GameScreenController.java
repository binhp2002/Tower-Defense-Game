package com.example.towerdefence.Controllers;
import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.enemy.*;
import com.example.towerdefence.objects.tower.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;


public class GameScreenController {
    private Player player;
    private Monument monument;

    private Scene nextScene;

    private List<Integer> currWaveAnimationCode;

    private List<Enemy> currWaveEnemyList;

    private int waveCount = 1;

    //number of waves in game
    private int numWaves = 2;

    private HashMap<GridPane, TowerRow> gameTowerRow = new HashMap<>();

    public Scene getNextScene() {
        return this.nextScene;
    }

    public List<Enemy> getCurrWaveEnemyList() {
        return this.currWaveEnemyList;
    }

    public void setCurrWaveEnemyList(List<Enemy> currWaveEnemyList) {
        this.currWaveEnemyList = currWaveEnemyList;
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

    public ArrayList<Integer> getCurrWaveAnimationCode() {
        //return a copy of the ArrayList
        return new ArrayList<>(this.currWaveAnimationCode);
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
     * method to prepare the list of enemies for the next wave
     * @param gamePath game path to determine where to place the enemies
     */
    public void setNextWave(Pane gamePath) {

        //always generate the same set of enemies for now
        List<Enemy> enemyList = new ArrayList<>();
        if (this.waveCount == this.numWaves) {
            enemyList.add(new BossEnemy((int) gamePath.getWidth(), 50));
        }
        else {
            for (int i = 0; i < 5; i++) {
                enemyList.add(new BasicEnemy((int) gamePath.getWidth(), i * 20));
            }
            for (int i = 5; i < 10; i++) {
                enemyList.add(new TankEnemy((int) gamePath.getWidth(), i * 20));
            }
            for (int i = 3; i < 7; i++) {
                enemyList.add(new FastEnemy((int) gamePath.getWidth(), i * 20));
            }
        }

        this.currWaveEnemyList = enemyList;
    }

    /**
     * logic for the while loop that occurs when an enemy wave is in progress
     * @param enemyWave EnemyWave object representing the enemies in the current wave
     * @param enemyVBoxHashMap mapping from enemies to their respective
     *                              ImageView objects in gamePath
     * @param currScene current Scene
     * @return animationIP ArrayList used to track if the wave is in progress
     */
    public ArrayList<Integer> gameMovementLoop(EnemyWave enemyWave, HashMap<Enemy,
            VBox> enemyVBoxHashMap, Scene currScene) {
        //store whether the animation is in progress, empty if not, one element if there is
        ArrayList<Integer> animationIP = new ArrayList<>();

        animationIP.add(1);

        long startTime = System.currentTimeMillis();

        AnimationTimer animation = new AnimationTimer() {
            private double prev = 0.0;

            private final double frameTime = 6 * Math.pow(10, 7);

            @Override
            public void handle(long now) {
                if (now - prev < frameTime) {
                    return;
                }
                player.setMoney(player.getMoney() + 1);
                this.prev = now;

                //get the enemies that have reached the end
                List<Enemy> enemiesReached = enemyWave.moveEnemiesForward();

                List<Enemy> deadEnemies = new ArrayList<>();

                for (TowerRow towerRow: gameTowerRow.values()) {
                    for (Enemy deadEnemy: towerRow.damageEnemies(enemyWave, now)) {
                        deadEnemies.add(deadEnemy);

                        removeFromGamePath(deadEnemy, enemyVBoxHashMap, currScene);
                    }

                }

                for (Enemy deadEnemy: deadEnemies) {
                    //add money based on enemies killed and increment enemy killed
                    player.setMoney(player.getMoney() + deadEnemy.getReward());
                    player.enemyKilled();
                }

                for (Enemy enemy: enemiesReached) {
                    //store the image view for the enemy then remove it so no longer seen
                    removeFromGamePath(enemy, enemyVBoxHashMap, currScene);

                    //enemies doing damage to monument
                    monument.setHealth(monument.getHealth() - enemy.getDamage());
                    if (monument.getHealth() <= 0) {
                        //game over
                        gameOver((StackPane) currScene.lookup("#gameOverPane"));

                        if (animationIP.size() == 1) {
                            //might have race condition where another handle call comes in while
                            //processing previous call
                            animationIP.remove(0);
                        }
                        //increment playtime
                        player.incrementPlayTime((int) (System.currentTimeMillis() - startTime));
                        this.stop();
                    }
                }

                for (Enemy enemy: enemyWave.getEnemies()) {
                    if (!enemyVBoxHashMap.containsKey(enemy)) {
                        throw new RuntimeException("enemy not in enemyWave.getEnemies, "
                                + "check if all killed enemies have been removed from "
                                + "enemyImageViewHashMap");
                    }

                    //the remaining enemies that have not been removed yet
                    updateEnemyBox(enemy, enemyVBoxHashMap.get(enemy));
                }

                //update player parameters
                ((Text) currScene.lookup("#playerParameters")).setText("Money: "
                        + player.getMoney() + " Health: " + monument.getHealth());
                if (enemyWave.getEnemies().isEmpty()) {
                    //current wave is over when there are no more enemies
                    //clear remaining enemies
                    ((Pane) currScene.lookup("#gamePath")).getChildren().clear();
                    if (animationIP.size() == 1) {
                        //might have race condition where another handle call comes in while
                        //processing previous call
                        animationIP.remove(0);
                    }
                    //increment playtime
                    player.incrementPlayTime((int) (System.currentTimeMillis() - startTime));

                    //if finished final wave
                    if (waveCount == numWaves) {
                        gameWin((Stage) currScene.getWindow());
                    }

                    waveCount++;
                    this.stop();
                }
            }
        };

        animation.start();

        return animationIP;
    }

    public void removeFromGamePath(Enemy enemy, HashMap<Enemy, VBox> enemyVBoxHashMap,
                                   Scene currScene) {

        //remove ImageView from gamePath
        Pane gamePath = (Pane) currScene.lookup("#gamePath");

        VBox enemyBox = enemyVBoxHashMap.get(enemy);

        //remove them from the HashMap so they aren't redrawn
        enemyVBoxHashMap.remove(enemy);

        gamePath.getChildren().remove(enemyBox);
    }

    /**
     * controller for start combat button, initializes the enemy wave
     * and then calls game movement loop
     * @param actionEvent event object for start combat button click
     */
    @FXML
    public void startCombatButton(ActionEvent actionEvent) {
        if (this.currWaveAnimationCode != null && this.currWaveAnimationCode.size() != 0) {
            //there is a current wave ongoing, terminate
            return;
        }

        Scene currScene = ((Node) actionEvent.getSource()).getScene();

        Pane gamePath = (Pane) currScene.lookup("#gamePath");

        EnemyWave enemyWave = new EnemyWave(new int[]{(int) gamePath.getLayoutX(),
            (int) gamePath.getLayoutY()});

        //creates associated enemies
        HashMap<Enemy, VBox> enemyVBoxHashMap = new HashMap<>();

        if (this.currWaveEnemyList == null) {
            //set next wave of enemies
            this.setNextWave(gamePath);
        }


        this.initializeEnemies(this.currWaveEnemyList, enemyWave, enemyVBoxHashMap, currScene);

        this.currWaveAnimationCode = gameMovementLoop(enemyWave, enemyVBoxHashMap, currScene);

        this.currWaveEnemyList = null;

    }

    /**
     * initializes enemies in a List of enemies by adding
     * the enemies to an enemyWave and creating ImageView
     * objects and rectangle object to represent health
     * for them in the current scene
     *
     * @param enemyList list of enemies to initialize
     * @param enemyWave enemyWave object to add enemies to
     * @param enemyVBoxHashMap HashMap mapping enemies to
     *                              ImageView objects, enemies added to this
     * @param currScene current scene
     */
    public void initializeEnemies(List<Enemy> enemyList, EnemyWave enemyWave,
                                  HashMap<Enemy, VBox> enemyVBoxHashMap, Scene currScene) {
        for (Enemy enemy: enemyList) {
            //add the enemy and add it to the scene as well
            enemyWave.addEnemy(enemy);
            enemyVBoxHashMap.put(enemy, createEnemyImage(enemy, currScene));
        }
    }

    /**
     * creates the enemy image and inserts it to the scene
     * @param enemy Enemy object
     * @param scene current scene
     * @return HBox object created for the Enemy object in the scene
     */
    public VBox createEnemyImage(Enemy enemy, Scene scene) {
        //get the stack pane to add the elements to it
        Pane gamePath = (Pane) scene.lookup("#gamePath");
        VBox enemyBox = new VBox();
        ImageView enemyImageView = new ImageView(enemy.getImagePath());
        //create enemy height and width
        enemyImageView.setFitHeight(enemy.getHeight());
        enemyImageView.setFitWidth(enemy.getWidth());

        Rectangle enemyHealthBar =  createEnemyHBar(enemy);

        //add enemyHealthBar to the enemyBox
        enemyBox.getChildren().add(enemyHealthBar);

        //add enemyImageView to the enemyBox
        enemyBox.getChildren().add(enemyImageView);

        //set position of the enemyBox within the gamePath
        enemyBox.setLayoutX(enemy.getRelativeLocation()[0]);
        enemyBox.setLayoutY(enemy.getRelativeLocation()[1]);
        gamePath.getChildren().add(enemyBox);

        //returns the ImageView associated with this enemy
        return enemyBox;
    }


    /**
     * updates the enemy image view location for the enemy
     * @param enemy enemy that we are updating the image of
     * @param enemyBox enemy Box object that contains enemy ImageView and enemy health bar
     */
    public void updateEnemyBox(Enemy enemy, VBox enemyBox) {
        //update enemyBox position
        enemyBox.setLayoutX(enemy.getRelativeLocation()[0]);
        enemyBox.setLayoutY(enemy.getRelativeLocation()[1]);

        //update enemyBox health bar
        if (!(enemyBox.getChildren().get(0) instanceof Rectangle)) {
            //the first element should be the health bar
            //runtime exception
            throw new RuntimeException("Health bar is not first element in enemyBox children");
        }

        Rectangle healthBar = (Rectangle) enemyBox.getChildren().get(0);

        // Update the health bar size
        updateEnemyHBar(enemy, healthBar);

    }

    /**
     * creates a enemy health bar above enemy
     * Use this method when creating enemies
     * The method uses the enemy and scene to appropriate the location and health
     * @param enemy enemy object
     * @return Recntangle for enemy health bar
     */
    public Rectangle createEnemyHBar(Enemy enemy) {
        // get the full health of enemy based off enemy type
        int fullHealth = enemy.getFullHealth();

        //Make new health bar
        Rectangle rec = new Rectangle();
        ///Set health bar width to enemy width at full health
        rec.setWidth(enemy.getWidth());

        rec.setHeight(5);
        rec.setFill(Color.RED);

        ///return the made rectangle
        return rec;
    }

    /**
     * updates the size of enemy health bar above enemy
     * Use this method when health of enemy changes such as taking damage
     * Or when enemy changes location
     * The method uses the enemy and the original
     * rectangle to appropriate size and location
     * @param enemy enemy object
     * @param rec enemy health bar that needs to be updated
     */
    public void updateEnemyHBar(Enemy enemy, Rectangle rec) {
        // get the full health of enemy based off enemy type
        int fullHealth = enemy.getFullHealth();

        /// Update the health bar size
        /// health is the percentage of enemy health from full health * 20
        rec.setWidth(((double) (enemy.getHealth()) / fullHealth) * enemy.getWidth());

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

            //get the absolute location of the tower by looking at the index
            // and then taking the center for that index
            int towerX = (int) (node.getLayoutX() + (colIndex + 0.5) * cellWidth);
            int towerY = (int) (node.getLayoutY() + (rowIndex + 0.5) * cellHeight);

            Tower tower = Tower.createTower(this.player.getCurrSelected(), towerX, towerY);

            if (!gameTowerRow.containsKey(node)) {
                //create the towerRow now in gameTowerRow since not inside
                gameTowerRow.put(node, new TowerRow(node));
            }

            //get the appropriate tower row for the one that was just click
            TowerRow towerRow = gameTowerRow.get(node);

            //check if tower exists in towerRow
            //if tower exists, insert upgraded tower
            if (towerRow.checkTower(rowIndex, colIndex) != null) {
                if (towerRow.checkTower(rowIndex, colIndex).isAssignableFrom(player.getCurrSelected())) {
                    Tower currentTower = towerRow.getTower(rowIndex, colIndex);
                    if (currentTower.upgradeTower() == -1) {
                        //tower is already max level, throw alert and don't do anything else
                        Alert a = new Alert(Alert.AlertType.ERROR);

                        a.setContentText("Tower is already at max level");

                        // show the dialog
                        a.show();
                        return;

                    }
                    ImageView cell = (ImageView) getNodeByCoordinate(rowIndex, colIndex, node);
                    cell.setImage(new Image(currentTower.getImagePath()));
                    cell.setFitHeight(cellHeight);
                    cell.setFitWidth(cellWidth);
                    player.setCurrSelected(null);
                    return;
                }

            } else {
                //insert the tower to that tower row
                towerRow.insertTower(rowIndex, colIndex, tower);
            }


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

    public void gameOver(StackPane gameOverPane) {
        gameOverPane.setVisible(true);
        gameOverPane.setStyle("-fx-background-color: transparent;");
    }

    public void gameWin(Stage stage) {
        //set up the statistics
        ((Text) this.nextScene.lookup("#enemiesKilled"))
                .setText("Enemies Killed: " + player.getEnemiesKilled());
        ((Text) this.nextScene.lookup("#timePassed"))
                .setText("Total Play Time: " + player.getPlayTime() / 1000 + " sec");
        ((Text) this.nextScene.lookup("#finalHealth"))
                .setText("Final Health: " + monument.getHealth());

        //set next scene
        stage.setScene(this.nextScene);
        stage.setTitle("Win Game Screen");
    }
}