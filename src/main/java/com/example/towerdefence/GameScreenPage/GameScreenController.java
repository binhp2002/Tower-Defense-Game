package com.example.towerdefence.GameScreenPage;
import com.example.towerdefence.objects.Monument;
import com.example.towerdefence.objects.Player;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameScreenController {

    private Scene nextScene;

    public Scene getNextScene() {
        return this.nextScene;
    }

    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

}
