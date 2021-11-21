package com.example.towerdefence.Controllers;
import com.example.towerdefence.objects.*;
import com.example.towerdefence.objects.tower.*;
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

    public void setNextScene(Scene scene) {this.nextScene = scene;}

    @FXML
    public void quitButton(ActionEvent ae) {
        System.exit(0);
    }

    @FXML
    public void againButton(ActionEvent ae) {
        clear();
        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(this.nextScene);
        stage.setTitle("Start Up");
    }

    public void clear() {
        player.setMoney(0);
        monument.setHealth(0);
    }
}