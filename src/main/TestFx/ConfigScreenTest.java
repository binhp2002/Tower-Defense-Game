
import com.example.towerdefence.GameApplication;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Test;
import javafx.embed.swing.SwingNode;
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
        assertEquals(stage.getTitle(), "Game Configuration");
        clickOn("#entry").write(" ");
        clickOn("#enter");
        verifyThat("#namePrompt", hasText("Name: Name is not allow, try again"));
    }
}
