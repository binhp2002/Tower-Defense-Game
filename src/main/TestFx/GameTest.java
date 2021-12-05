import com.example.towerdefence.Controllers.*;
import com.example.towerdefence.GameApplication;
import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.enemy.*;
import com.example.towerdefence.objects.tower.*;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;


public class GameTest extends ApplicationTest {
    private Stage stage;
    private GameApplication main;
    private Player player;
    private Monument monument;
    private Scene gameScene;
    private GameScreenController gameScreenController;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //start game and navigate to medium difficulty page
        this.main = new GameApplication();
        this.main.start(primaryStage);
        this.player = main.getPlayer();
        this.monument = main.getMonument();
        this.stage = primaryStage;
        this.gameScreenController =
                (GameScreenController) this.main.getControllerMap().get("GameScreenController");
    }

    /**
     * Setting up the test environment to get the the medium difficulty startgame screen page
     */
    @Before
    public void setUp() {
        //get to medium difficulty start game screen
        //navigate to medium difficulty start game page
        clickOn("#startGameButton");
        //check if on Game Configuration Page
        assertEquals(stage.getTitle(), "Game Configuration");
        //select easy difficulty
        clickOn("#easy");
        //enter name
        clickOn("#entry").write("test");
        clickOn("#enter");
        clickOn("#startGame");
        assertEquals(stage.getTitle(), "Tower Defense Game");
        //get the game scene after navigating to that scene
        this.gameScene = stage.getScene();

        Pane gamePath = (Pane) this.gameScene.lookup("#gamePath");
        //set up the first wave of enemies
        List<Enemy> enemyList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            enemyList.add(new BasicEnemy((int) gamePath.getWidth(), i * 20));
        }
        for (int i = 5; i < 10; i++) {
            enemyList.add(new TankEnemy((int) gamePath.getWidth(), i * 20));
        }

        this.gameScreenController.setCurrWaveEnemyList(enemyList);
    }

    /**
     * test buying tower and ensure that the player parameters (money) was appropriately updated
     */
    @Test
    public void testBuyTower() {
        //attempt to buy sniper tower
        clickOn("#SniperTowerPurchaseButton");

        int correctPlayerMoneyLeft = (int) (1000 - SniperTower.BASIC_COST);

        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);

        //check that player parameters were changed appropriately on the screen
        verifyThat("#playerParameters", (Text t) ->
                t.getText().contains("Money: " + correctPlayerMoneyLeft)
                && t.getText().contains("Health: 150"));
    }

    /**
     * test buying tower with insufficient money, check that money amount unchanged and
     * currSelected in Player object is still null (i.e. tower not bought)
     */
    @Test
    public void buyTowerNoMoney() {
        //set player to have very little money, not enough to buy tower
        assertEquals(this.player.setMoney(50), 0);

        int correctPlayerMoneyLeft = this.player.getMoney();

        //attempt to buy sniper tower
        clickOn("#SniperTowerPurchaseButton");

        //check that money is left unchanged, cannot check the UI because we manually
        // changed the player's money so not action event triggered to update money
        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);

        //no tower should be selected
        assertNull(this.player.getCurrSelected());
    }

    /**
     * check if left over money after buying basic tower is correct
     */
    @Test
    public void leftOverMoneyBasic() {
        //attempt to buy basic tower
        clickOn("#BasicTowerPurchaseButton");

        int correctPlayerMoneyLeft = (int) (1000 - BasicTower.BASIC_COST);

        //verify that the correct leftover money was shown to the user after purchase a basic tower
        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);
        //verify that player curr selected now includes the basic tower
        assertEquals(this.player.getCurrSelected(), BasicTower.class);
    }

    /**
     * check if amount of money after buying sniper tower is correct
     */
    @Test
    public void leftOverMoneySniper() {
        //attempt to buy sniper tower
        clickOn("#SniperTowerPurchaseButton");

        int correctPlayerMoneyLeft = (int) (1000 - SniperTower.BASIC_COST);

        //verify that the correct leftover money was shown to the user after purchase a sniper tower
        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);
        //verify that player curr selected now includes the sniper tower
        assertEquals(this.player.getCurrSelected(), SniperTower.class);
    }

    /**
     * check if amount of money after buying machine tower is correct
     */
    @Test
    public void leftOverMoneyMachine() {
        //attempt to buy machine tower
        clickOn("#MachineTowerPurchaseButton");

        int correctPlayerMoneyLeft = (int) (1000 - MachineTower.BASIC_COST);

        //verify that the correct leftover money was shown to the user after
        // purchase a machine tower
        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);
        //verify that player curr selected now includes the machine tower
        assertEquals(this.player.getCurrSelected(), MachineTower.class);
    }

    /**
     * check if tower can be placed on top row by checking if PLayer currSelected is set to null
     */
    @Test
    public void placeTowerTopRow() {
        clickOn("#BasicTowerPurchaseButton");
        GridPane topTowerRow = (GridPane) gameScene.lookup("#topTowerRow");
        clickOn(point(stage.getX() + topTowerRow.getLayoutX() + 10,
                stage.getY() + topTowerRow.getLayoutY() + 10));
        //player curr selected is null and no longer with the player
        assertNull(this.player.getCurrSelected());
    }

    /**
     * attempt to place tower on path and verify not done by checking if Player
     * currSelected is not null
     */
    @Test
    public void placeTowerPath() {
        clickOn("#BasicTowerPurchaseButton");
        Pane path = (Pane) gameScene.lookup("#gamePath");
        clickOn(point(stage.getX() + path.getLayoutX() + 10,
                stage.getY() + path.getLayoutY() + 10));
        //player curr selected is not null, still with player
        assertNotNull(this.player.getCurrSelected());
    }

    /**
     * check if tower can be placed on bottom row by checking if PLayer currSelected is set to null
     */
    @Test
    public void placeTowerBottomRow() {
        clickOn("#BasicTowerPurchaseButton");
        //click on the bottom row
        GridPane bottomTowerRow = (GridPane) gameScene.lookup("#bottomTowerRow");
        clickOn(point(stage.getX() + bottomTowerRow.getLayoutX() + 10,
                stage.getY() + bottomTowerRow.getLayoutY() + 10));
        //player curr selected is null and no longer with the player
        assertNull(this.player.getCurrSelected());
    }

    /**
     * check if there is enemy once click start combat button and curr wave animation code changed
     */
    @Test
    public void checkEnemy() {
        clickOn("#startCombatButton");
        assertArrayEquals(this.gameScreenController.getCurrWaveAnimationCode().toArray(),
                new Integer[]{1});
        Pane gamePath = (Pane) gameScene.lookup("#gamePath");
        assertNotNull(gamePath.getChildren());
    }

    /**
     * check if each enemy has an image and a corresponding health bar
     */
    @Test
    public void checkEnemyHealthBarPresent() {
        clickOn("#startCombatButton");
        Pane gamePath = (Pane) gameScene.lookup("#gamePath");
        assertNotNull(gamePath.getChildren());
        for (Node enemyBox: gamePath.getChildren()) {
            //check if children in GamePath are enemyBox
            assertTrue(enemyBox instanceof VBox);
            //check if the first element in each enemyBox is a health bar
            assertTrue(((VBox) enemyBox).getChildren().get(0) instanceof Rectangle);
            //check if the second element in each enemyBox is an enemy image
            assertTrue(((VBox) enemyBox).getChildren().get(1) instanceof ImageView);

        }
    }

    /**
     * check if enemy healthbar size decreases the correct amount as enemy takes damage
     */
    @Test
    public void checkEnemyHealthBarDecrease() {
        Pane gamePath = (Pane) gameScene.lookup("#gamePath");
        Enemy enemy = new BasicEnemy((int) gamePath.getWidth(), 20);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameScreenController.createEnemyImage(enemy, gameScene);
                Rectangle rec = gameScreenController.createEnemyHBar(enemy);

                double origLength = rec.getWidth();

                // Enemy take damage
                enemy.setHealth(enemy.getHealth() / 2);

                gameScreenController.updateEnemyHBar(enemy, rec);

                //Check after enemy takes damage
                assertEquals(rec.getWidth(), origLength / 2, 0.5);
            }
        });
    }



    /**
     * check if the basic tower purchase button can be click after heath = 0
     */
    @Test
    public void gameEndClickBasic() {
        while (monument.getHealth() > 0) {
            clickOn("#startCombatButton");
        }
        clickOn("#BasicTowerPurchaseButton");
        assertNull(this.player.getCurrSelected());
    }
    /**
     * check if the sniper tower purchase button can be clicked after heath = 0
     */
    @Test
    public void gameEndClickSniper() {
        while (monument.getHealth() > 0) {
            clickOn("#startCombatButton");
        }
        clickOn("#SniperTowerPurchaseButton");
        assertNull(this.player.getCurrSelected());
    }
    /**
     * check if the machine tower purchase button can be clicked after heath = 0
     */
    @Test
    public void gameEndClickMachine() {
        while (monument.getHealth() > 0) {
            clickOn("#startCombatButton");
        }
        clickOn("#MachineTowerPurchaseButton");
        assertNull(this.player.getCurrSelected());
    }

    /**
     * check if the combat button can be clicked after heath = 0
     */
    @Test
    public void gameEndClickCombat() {
        while (monument.getHealth() > 0) {
            clickOn("#startCombatButton");
        }
        clickOn("#startCombatButton");
        assertNull(this.player.getCurrSelected());
    }


    /**
     * test if animation has stopped by checking if animation code changes back to empty and
     * letting game run for one more sec to see if enemy positions have been changed
     * (difficult to access FXML elements directly cause JavaFX doesn't seem to allow
     * non-application threads to alter UI elements)
     */
    @Test
    public void checkGameEndStopAnimation() {
        //store enemyList to check if enemies still moving

        //store list of current enemies to ensure to check if enemy positions change later on
        List<Enemy> enemies = this.gameScreenController.getCurrWaveEnemyList();

        while (monument.getHealth() > 0) {
            clickOn("#startCombatButton");
        }

        List<int[]> prevEnemyLocations = new ArrayList<>();

        for (Enemy enemy: enemies) {
            prevEnemyLocations.add(enemy.getRelativeLocation());
        }

        //get relative locations of the enemies

        try {
            //give some time delay
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        List<int[]> afterEnemyLocations = new ArrayList<>();

        for (Enemy enemy: enemies) {
            afterEnemyLocations.add(enemy.getRelativeLocation());
        }

        assertArrayEquals(this.gameScreenController.getCurrWaveAnimationCode().toArray(),
                new Integer[]{});
        //check if anything moved in that time break
        assertArrayEquals(prevEnemyLocations.toArray(), afterEnemyLocations.toArray());

    }

    /**
     * check if tower can be placed on top row by checking if PLayer currSelected is set to null
     */
    @Test
    public void placeTowerTopRowDuringWave() {
        //start a wave
        clickOn("#startCombatButton");
        //buy and place tower
        clickOn("#BasicTowerPurchaseButton");
        GridPane topTowerRow = (GridPane) gameScene.lookup("#topTowerRow");
        clickOn(point(stage.getX() + topTowerRow.getLayoutX() + 10,
                stage.getY() + topTowerRow.getLayoutY() + 10));
        //player curr selected is null and no longer with the player
        assertNull(this.player.getCurrSelected());
    }

    @Test
    public void playerMoneyIncrease() {
        clickOn("#startCombatButton");
        int playerInitialMoney = this.player.getMoney();

        try {
            //give some time delay
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        //check that player money has increased overtime
        assertTrue(this.player.getMoney() > playerInitialMoney);

    }


    /**
     * Tests that when enemies die, it gets removed off the screen/game path
     */
    @Test
    public void deadEnemyRemoval() {
        Pane gamePath = (Pane) gameScene.lookup("#gamePath");
        boolean enemykilled = false;

        //Create Tower place on map
        clickOn("#BasicTowerPurchaseButton");
        GridPane topTowerRow = (GridPane) gameScene.lookup("#topTowerRow");
        clickOn(point(stage.getX() + topTowerRow.getLayoutX() + 500,
                stage.getY() + topTowerRow.getLayoutY() + 100));

        // Create enemies
        List<Enemy> enemyList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            enemyList.add(new BasicEnemy((int) gamePath.getWidth(), i * 20));
        }


        // Start game
        gameScreenController.setCurrWaveEnemyList(enemyList);
        clickOn("#startCombatButton");

        int oriGameChildren = gamePath.getChildren().size();
        //Check every 1000 millisecs
        while (enemyList.get(0).getHealth() > 0) {
            try {
                //give some time delay
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            if (gamePath.getChildren().size() < oriGameChildren) {
                enemykilled = true;
            }
        }
        //Check gamepath is empty and the enemies are removed
        assertTrue(enemykilled);

    }


    @Test
    public void enemyHealthDecrease() {
        List<Enemy> enemyList = this.gameScreenController.getCurrWaveEnemyList();

        List<Integer> enemyInitialHealth = new ArrayList<>();
        List<Integer> enemyFinalHealth = new ArrayList<>();

        //buy and place a basic tower
        clickOn("#BasicTowerPurchaseButton");
        GridPane topTowerRow = (GridPane) gameScene.lookup("#topTowerRow");
        clickOn(point(stage.getX() + topTowerRow.getLayoutX() + 10,
                stage.getY() + topTowerRow.getLayoutY() + 10));

        //get initial health of enemy
        for (Enemy enemy : enemyList) {
            enemyInitialHealth.add(enemy.getHealth());
        }

        //start the wave
        clickOn("#startCombatButton");

        try {
            //give some time delay
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }

        //get final health of enemy
        for (Enemy enemy : enemyList) {
            enemyFinalHealth.add(enemy.getHealth());
        }

        assertNotEquals(enemyInitialHealth, enemyFinalHealth);
    }

    /**
     * check if the play again button lead back to first page
     */
    @Test
    public void testPlayAgain() {
        //buy and place the first sniper tower
        clickOn("#SniperTowerPurchaseButton");
        GridPane topTowerRow = (GridPane) gameScene.lookup("#topTowerRow");
        clickOn(point(stage.getX() + topTowerRow.getLayoutX() + 300,
                stage.getY() + topTowerRow.getLayoutY() + 10));

        //buy and place the second basic tower
        clickOn("#BasicTowerPurchaseButton");
        GridPane bottomTowerRow = (GridPane) gameScene.lookup("#bottomTowerRow");
        clickOn(point(stage.getX() + bottomTowerRow.getLayoutX() + 300,
                stage.getY() + bottomTowerRow.getLayoutY() + 10));

        //buy and place the third basic tower
        clickOn("#BasicTowerPurchaseButton");
        clickOn(point(stage.getX() + bottomTowerRow.getLayoutX() + 300,
                stage.getY() + bottomTowerRow.getLayoutY() + 10));

        //start the first wave
        clickOn("#startCombatButton");

        try {
            //give some time delay
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println(e);
        }

        clickOn("#SniperTowerPurchaseButton");
        topTowerRow = (GridPane) gameScene.lookup("#topTowerRow");
        clickOn(point(stage.getX() + topTowerRow.getLayoutX() + 300,
                stage.getY() + topTowerRow.getLayoutY() + 10));

        //start the second wave
        clickOn("#startCombatButton");

        try {
            //give some time delay
            Thread.sleep(20000);
        } catch (Exception e) {
            System.out.println(e);
        }

        clickOn("#againButton");
        assertEquals(stage.getTitle(), "Tower Defense Game");
    }


    @Test
    public void testExitButton() {
        //buy and place the first sniper tower
        clickOn("#SniperTowerPurchaseButton");
        GridPane topTowerRow = (GridPane) gameScene.lookup("#topTowerRow");
        clickOn(point(stage.getX() + topTowerRow.getLayoutX() + 300,
                stage.getY() + topTowerRow.getLayoutY() + 10));

        //buy and place the second basic tower
        clickOn("#BasicTowerPurchaseButton");
        GridPane bottomTowerRow = (GridPane) gameScene.lookup("#bottomTowerRow");
        clickOn(point(stage.getX() + bottomTowerRow.getLayoutX() + 300,
                stage.getY() + bottomTowerRow.getLayoutY() + 10));

        //buy and place the third basic tower
        clickOn("#BasicTowerPurchaseButton");
        clickOn(point(stage.getX() + bottomTowerRow.getLayoutX() + 300,
                stage.getY() + bottomTowerRow.getLayoutY() + 10));

        //start the first wave
        clickOn("#startCombatButton");

        try {
            //give some time delay
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }

       clickOn("#SniperTowerPurchaseButton");
       topTowerRow = (GridPane) gameScene.lookup("#topTowerRow");
       clickOn(point(stage.getX() + topTowerRow.getLayoutX() + 300,
               stage.getY() + topTowerRow.getLayoutY() + 10));

       //start the second wave
       clickOn("#startCombatButton");

       try {
            //give some time delay
            Thread.sleep(20000);
        } catch (Exception e) {
            System.out.println(e);
        }

        clickOn("#quitButton");
        assertFalse(this.stage.isShowing());
    }
}
