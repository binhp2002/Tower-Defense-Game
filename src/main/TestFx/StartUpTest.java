import com.example.towerdefence.GameApplication;
import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.tower.*;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

public class StartUpTest extends ApplicationTest {

    private Stage stage;
    private GameApplication main;
    private Player player;


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

    /**
     * to test that images on the game screen can be appropriately loaded into the Image class
     */
    @Test
    public void testImagesLoading() {
        //testing basic tower image path
        assertThat(new Image((new BasicTower(new int[]{0, 0})).getImagePath()),
                instanceOf(Image.class));
        //testing sniper tower image path
        assertThat(new Image((new SniperTower(new int[]{0, 0})).getImagePath()),
                instanceOf(Image.class));
        //testing machine tower image path
        assertThat(new Image((new MachineTower(new int[]{0, 0})).getImagePath()),
                instanceOf(Image.class));
    }
}
