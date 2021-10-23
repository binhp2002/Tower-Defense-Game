import com.example.towerdefence.GameApplication;
import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.tower.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;


public class GameTest extends ApplicationTest {
    private Stage stage;
    private GameApplication main;
    private Player player;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //start game and navigate to medium difficulty page
        main = new GameApplication();
        this.main.start(primaryStage);
        this.player = main.getPlayer();
        stage = primaryStage;
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
        clickOn("#medium");
        //enter name
        clickOn("#entry").write("test");
        clickOn("#enter");
        clickOn("#startGame");
        assertEquals(stage.getTitle(), "tower Defense Game");
        verifyThat("#playerParameters", (Text t) -> t.getText().contains("Money: 500")
                && t.getText().contains("Health: 100"));
    }

    /**
     * test buying tower and ensure that the player parameters (money) was appropriately updated
     */
    @Test
    public void testBuyTower() {
        //attempt to buy sniper tower
        clickOn("#SniperTowerPurchaseButton");

        int correctPlayerMoneyLeft = (int) (500 - 1.5 * SniperTower.BASIC_COST);

        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);

        //check that player parameters were changed appropriately on the screen
        verifyThat("#playerParameters", (Text t) ->
                t.getText().contains("Money: " + correctPlayerMoneyLeft)
                && t.getText().contains("Health: 100"));
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

        int correctPlayerMoneyLeft = (int) (500 - 1.5 * BasicTower.BASIC_COST);

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

        int correctPlayerMoneyLeft = (int) (500 - 1.5 * SniperTower.BASIC_COST);

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

        int correctPlayerMoneyLeft = (int) (500 - 1.5 * MachineTower.BASIC_COST);

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
        GridPane topTowerRow = (GridPane) stage.getScene().lookup("#topTowerRow");
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
        GridPane path = (GridPane) stage.getScene().lookup("#gamePath");
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
        GridPane bottomTowerRow = (GridPane) stage.getScene().lookup("#bottomTowerRow");
        clickOn(point(stage.getX() + bottomTowerRow.getLayoutX() + 10,
                stage.getY() + bottomTowerRow.getLayoutY() + 10));
        //player curr selected is null and no longer with the player
        assertNull(this.player.getCurrSelected());
    }
}
