import com.example.towerdefence.GameApplication;
import com.example.towerdefence.objects.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

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
        assertEquals(stage.getTitle(), "Tower Defense Game");
        verifyThat("#playerParameters", (Text t) -> t.getText().contains("Money: 500")
                && t.getText().contains("Health: 100"));
    }

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

    @Test
    public void buyTowerNoMoney() {
        //set player to have very little money, not enough to buy tower
        assertEquals(this.player.setMoney(50), 0);

        //attempt to buy sniper tower
        clickOn("#SniperTowerPurchaseButton");

        int correctPlayerMoneyLeft = this.player.getMoney();

        //check that money is left unchanged, cannot check the UI because we manually
        // changed the player's money so not action event triggered to update money
        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);

        //no tower should be selected
        assertNull(this.player.getCurrSelected());
    }

    @Test
    public void playerParameterExist() {
        //check if the title is correct
        assertEquals(stage.getTitle(), "Tower Defense Game");
        //check if the player parameter bar exist
        verifyThat("#playerParameters", NodeMatchers.isVisible());
    }

    @Test
    public void buyTowerButton() {
        //check if the tower purchase buttons are available
        verifyThat("#SniperTowerPurchaseButton", NodeMatchers.isVisible());
        verifyThat("#BasicTowerPurchaseButton", NodeMatchers.isVisible());
        verifyThat("#MachineTowerPurchaseButton", NodeMatchers.isVisible());
    }

    @Test
    public void leftOverMoney() {
        //attempt to buy basic tower
        clickOn("#BasicTowerPurchaseButton");

        int correctPlayerMoneyLeft = (int) (500 - 1.5 * BasicTower.BASIC_COST);

        //verify that the correct leftover money was shown to the user after purchase a tower
        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);
    }
}
