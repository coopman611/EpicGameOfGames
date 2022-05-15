/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.gameattempt;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class GameStart extends Application {

    private Pane root = new Pane();

    private double t = 0;

    private Player player = new Player(300, 750, 40, 40, "player", Color.BLUE);
    

    private Parent createContent() {
        root.setPrefSize(600, 800);

        root.getChildren().add(player);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        root.getChildren().add(new Enemy(500, 100, 25, 25, "1", Color.ALICEBLUE));
        root.getChildren().add(new Enemy(400, 100, 25, 25, "2", Color.ANTIQUEWHITE));
        root.getChildren().add(new Enemy(300, 100, 25, 25, "mook3", Color.AQUA));
        root.getChildren().add(new Enemy(200, 100, 25, 25, "mook4", Color.AQUAMARINE));
        root.getChildren().add(new Enemy(100, 100, 25, 25, "mook5", Color.AZURE));
        root.getChildren().add(new Enemy(0, 100, 25, 25, "mook6", Color.BEIGE));
        root.getChildren().add(new Enemy(50, 100, 25, 25, "mook7", Color.BISQUE));
        root.getChildren().add(new Enemy(150, 100, 25, 25, "mook8", Color.BLANCHEDALMOND));
        root.getChildren().add(new Enemy(250, 100, 25, 25, "mook9", Color.BLUE));
        root.getChildren().add(new Enemy(350, 100, 25, 25, "mook10", Color.BLUEVIOLET));
        root.getChildren().add(new Enemy(450, 100, 25, 25, "mook11", Color.BROWN));
        root.getChildren().add(new Enemy(550, 100, 25, 25, "mook12", Color.BURLYWOOD));

        timer.start();

        return root;
    }

    private void update() {
        t += 0.016;

        if (t > 2) {
            t = 0;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;

            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main1(String[] args) {
        launch(args);
    }
}
