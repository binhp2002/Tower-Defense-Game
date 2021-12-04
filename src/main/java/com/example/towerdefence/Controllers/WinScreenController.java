package com.example.towerdefence.Controllers;
import com.example.towerdefence.GameApplication;
import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.tower.*;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinScreenController {
    private Scene nextScene;
    private Player player;
    private Monument monument;
    private Stage stage;

    public void setNextScene(Scene scene) {this.nextScene = scene;}

    public void setNewStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void quitButton(ActionEvent ae) {
        //player.setGameOver(true);
        //Platform.exit();
        stage.close();
    }

    @FXML
    public void againButton(ActionEvent ae) {
        clear();
    }

    public void clear() {
        GameApplication gameApplication = new GameApplication();
        try {
            gameApplication.start(this.stage);
        } catch (Exception e) {}
    }
}