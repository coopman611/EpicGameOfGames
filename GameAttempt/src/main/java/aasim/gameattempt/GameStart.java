/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.gameattempt;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author 14048
 */
public class GameStart extends Application {

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.centerOnScreen();
        GamePanel panel = new GamePanel();
        Scene scene = new Scene(panel);

        stage.setScene(scene);
        
        stage.show();
        stage.setOnCloseRequest(eh -> close());
        panel.startGameThread();
    }

    private void close() {
        System.exit(0);
    }

}
