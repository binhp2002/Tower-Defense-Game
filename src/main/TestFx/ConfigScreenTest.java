
import com.example.towerdefence.GameApplication;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


public class ConfigScreenTest extends ApplicationTest {
    private Stage stage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        GameApplication main = new GameApplication();
        main.start(primaryStage);
        stage = primaryStage;
    }

    @Test
    public void testConfigScreenName() {
        clickOn("#startGameButton");
        assertEquals(stage.getTitle(), "Game Configuration");
        System.out.println(stage.getTitle());
        clickOn("#easy");
        clickOn("#startGame");
        verifyThat("#incompletePrompt", hasText("Name: Name is not allow, try again"));
    }
}
