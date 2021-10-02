package com.example.towerdefence;
import com.example.towerdefence.objects.Monument;
import com.example.towerdefence.objects.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class GameApplication extends Application {

    private Stage window;
    private Scene gameScene;

    private Player player;
    private Monument monument;

    @Override
    public void start(Stage stage) throws IOException {
        this.player = new Player();
        this.monument = new Monument();
        this.window = stage;

        //use VBox layout for the initial start screen with a button and text
        VBox startVBox = new VBox();

        Label startGamePrompt = new Label();

        startGamePrompt.setText("Click button to start playing...");

        Button startGameButton = new Button();

        //set text for the game button
        startGameButton.setText("Start Game!");

        startGameButton.setOnAction(event -> {
            //call config screen event
            initializeConfigScreen();
        });

        //add the start game prompt on top of the start game button
        startVBox.getChildren().add(startGamePrompt);
        startVBox.getChildren().add(startGameButton);

        //Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        Scene initialScreenScene = new Scene(startVBox, 640, 480);
        stage.setTitle("Tower Defense Game");
        stage.setScene(initialScreenScene);
        stage.requestFocus();
        stage.show();

    }

    public void initializeConfigScreen() {
        BorderPane pane = new BorderPane();
        pane.setCenter(addGridPane());
        Scene scene = new Scene(pane, 640, 480);
        this.window.setScene(scene);
        this.window.setTitle("Game Configuration");
        this.window.show();
    }

    private void initializeGameScreen() {
        //StackPane map = new StackPane();
        HBox descriptionGrid = new HBox();
        HBox topLane = new HBox();
        HBox midLane = new HBox();
        HBox bottomLane = new HBox();
        VBox map = new VBox();
        map.getChildren().add(descriptionGrid);
        map.getChildren().add(topLane);
        map.getChildren().add(midLane);
        map.getChildren().add(bottomLane);
        Scene gameMapScene = new Scene(map, 1000, 600);
        gameMapScene.setFill(Color.WHITE);
        mapSetter(map, gameMapScene, descriptionGrid, topLane, midLane, bottomLane);
        displayGameParameters(descriptionGrid);
        //Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        window.setTitle("Tower Defense Game");
        window.setScene(gameMapScene);
        window.requestFocus();
        window.show();
    }

    private void mapSetter(VBox map, Scene scene, HBox descriptionGrid, HBox topLane, HBox midLane, HBox bottomLane) {
        //Rectangle initialiser
        Rectangle strip = new Rectangle(scene.getWidth(), 23);
        Rectangle monument= new Rectangle(70,180);

        //Action


        towerFiller(topLane);

        FileInputStream inputstream;

        //Add monument image
        try {
            inputstream = new FileInputStream("./src/main/resources/com/example/" +
                    "towerdefence/Images/monument.png");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, terminating");
            return;
        }
        Image img = new Image(inputstream);
        ImageView imgView = new ImageView(img);
        StackPane monStack = new StackPane();
        imgView.setFitHeight(150);
        imgView.setFitWidth(130);
        monStack.getChildren().add(imgView);
        midLane.getChildren().add(monStack);

        //
        enemyPositionGridSetter(midLane);
        towerFiller(bottomLane);

    }

    /**
     * Placeholder creator for the towers the user will insert
     * @param lane the lane where the towers will be inserted at
     */
    private void towerFiller(HBox lane) {
        int cnt = 50;
        while (cnt <= 1000) {
            Rectangle rect = new Rectangle(50, 180);
            rect.setStroke(Color.WHITE);
            rect.setFill(Color.GREEN);
            StackPane tower = new StackPane();
            tower.getChildren().addAll(rect, new Label("Tower"));
            lane.getChildren().add(tower);
            cnt += 50;
        }
    }
    private void enemyPositionGridSetter(HBox lane) {
        for(int i=0; i*20<=1000; i++){
            StackPane enemyPosition = new StackPane();
            Rectangle tile = new Rectangle(20,180);
            tile.setFill(Color.WHITE);
            tile.setStroke(Color.BLACK);
            tile.setStrokeWidth(1);
            enemyPosition.getChildren().add(tile);
            lane.getChildren().add(enemyPosition);
        }
    }

    private void displayGameParameters(HBox descriptionGrid) {
        Rectangle r1 = new Rectangle(200, 23);
        r1.setStroke(Color.RED);
        r1.setFill(Color.WHITE);

        String playerMoney =  String.valueOf(player.getMoney());
        String monumentHealth = String.valueOf(monument.getHealth());

        String playerParameterString = "Money: " + playerMoney + "   Health: " + monumentHealth;
        Text playerParameters = new Text(playerParameterString);
        StackPane descriptionPane = new StackPane();
        descriptionPane.getChildren().addAll(r1, playerParameters);
        descriptionGrid.getChildren().add(descriptionPane);
    }

    /**
     * Show the game and player information buttons at the center of the scene.
     * @return the game and player information buttons.
     */
    public GridPane addGridPane() {
        Text namePrompt;
        Text difficultyPrompt;
        Text moneyPrompt;
        Text incompletePrompt;
        TextField entry;
        String input = "None";
        int difficultySelection = 0;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        entry = new TextField();
        entry.setPromptText("Enter your name here");
        grid.add(entry, 1, 1);

        namePrompt = new Text("Name: " + input);
        grid.add(namePrompt, 1, 0);

        Button enter = new Button("Enter");
        grid.add(enter, 2, 1);
        enter.setOnMouseClicked(e -> {
            if (player.setName(entry.getText()) == -1) {
                namePrompt.setText("Name: Name is not allow, try again");
            } else {
                namePrompt.setText("Name: " + entry.getText());
            }
        });

        difficultyPrompt = new Text("Difficulty: " + difficultySelection);
        grid.add(difficultyPrompt, 1, 3);

        moneyPrompt = new Text("Money: " + 0);
        grid.add(moneyPrompt, 1, 4);

        Button easy = new Button("Easy");
        grid.add(easy, 2, 3);
        easy.setOnMouseClicked(e -> {
            player.setMoney(1000);
            player.setDifficulty(1);
            difficultyPrompt.setText("Difficulty: " + 1);
            moneyPrompt.setText("Money: " + 1000);
            monument.setHealth(150);
        });

        Button medium = new Button("Medium");
        grid.add(medium, 2, 4);
        medium.setOnMouseClicked(e -> {
            player.setMoney(500);
            player.setDifficulty(2);
            difficultyPrompt.setText("Difficulty: " + 2);
            moneyPrompt.setText("Money: " + 500);
            monument.setHealth(100);
        });

        Button hard = new Button("Hard");
        grid.add(hard, 2, 5);
        hard.setOnMouseClicked(e -> {
            player.setMoney(100);
            player.setDifficulty(3);
            difficultyPrompt.setText("Difficulty: " + 3);
            moneyPrompt.setText("Money: " + 100);
            monument.setHealth(50);
        });

        Button startGame = new Button("Start Game");
        grid.add(startGame, 3, 7);
        incompletePrompt = new Text();
        grid.add(incompletePrompt, 4, 7);
        startGame.setOnMouseClicked(e -> {
            if (player.getName() == null || player.getMoney() == 0) {
                //some of the player settings not selected
                incompletePrompt.setText("Please select a difficulty and name and try again");
            } else {
                this.initializeGameScreen();
            }
        });

        return grid;
    }

    public static void main(String[] args) {
        launch();
    }
}