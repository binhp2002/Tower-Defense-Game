import com.example.towerdefence.GameApplication;
import com.example.towerdefence.objects.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;


public class GameTest extends ApplicationTest {
    private Stage stage;
    GameApplication main;
    Player player;


    @Override
    public void start(Stage primaryStage) throws Exception {
        main = new GameApplication();
        this.main.start(primaryStage);
        this.player = main.getPlayer();
        stage = primaryStage;
    }

    /**
     * Test if landing page is correct and if button is there
     */
    @Test
    public void testLandingPage() {
        //check if the title is correct
        assertEquals(stage.getTitle(), "Tower Defense Game");
        verifyThat("#startGameButton", NodeMatchers.isVisible());
    }

    /**
     * Tests empty name inserted (no name inserted), expected to get incompletePrompt
     * telling user to select difficulty and name
     */
    @Test
    public void testConfigEmptyName() {
        clickOn("#startGameButton");
        //check if on Game Configuration page
        assertEquals(stage.getTitle(), "Game Configuration");
        clickOn("#easy");
        clickOn("#startGame");
        verifyThat("#incompletePrompt", (Text t) -> t.getText().contains("Please select "
                + "a difficulty and name and try again"));
    }

    /**
     * Tests no difficulty selected, expected to get incompletePrompt telling user to select
     * difficulty and name
     */
    @Test
    public void testConfigNoDifficulty() {
        clickOn("#startGameButton");
        //check if on Game Configuration page
        assertEquals(stage.getTitle(), "Game Configuration");
        //enter name
        clickOn("#entry").write("test");
        clickOn("#enter");
        //start game
        clickOn("#startGame");
        verifyThat("#incompletePrompt", (Text t) -> t.getText().contains("Please select "
                + "a difficulty and name and try again"));
    }

    /**
     * check if moves to game page with correct health and money on easy difficulty
     */
    @Test
    public void testConfigEasyDifficulty() {
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
        verifyThat("#playerParameters", (Text t) -> t.getText().contains("Money: 1000")
                && t.getText().contains("Health: 150"));
    }

    /**
     * check if moves to game page with correct health and money on medium difficulty
     */
    @Test
    public void testConfigMediumDifficulty() {
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

    /**
     * check if moves to game page with correct health and money on hard difficulty
     */
    @Test
    public void testConfigHardDifficulty() {
        clickOn("#startGameButton");
        //check if on Game Configuration Page
        assertEquals(stage.getTitle(), "Game Configuration");
        //select easy difficulty
        clickOn("#hard");
        //enter name
        clickOn("#entry").write("test");
        clickOn("#enter");
        clickOn("#startGame");
        assertEquals(stage.getTitle(), "Tower Defense Game");
        verifyThat("#playerParameters", (Text t) -> t.getText().contains("Money: 100")
                && t.getText().contains("Health: 50"));
    }

    @Test
    public void testBuyTower() {
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

        clickOn("#SniperTowerPurchaseButton");

        int correctPlayerMoneyLeft = (int) (500 - 1.5 * SniperTower.BASIC_COST);

        assertEquals(this.player.getMoney(), correctPlayerMoneyLeft);

        //after implemented check on changing the title money
//        verifyThat("#playerParameters", (Text t) -> t.getText().contains("Money: " + correctPlayerMoneyLeft)
//                && t.getText().contains("Health: 100"));
    }
}
