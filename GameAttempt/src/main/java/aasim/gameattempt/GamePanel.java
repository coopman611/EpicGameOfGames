/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.gameattempt;

import com.sun.prism.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 14048
 */
public class GamePanel extends StackPane implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 4;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    final int FPS = 60;
    //
    Thread gameThread;
    Canvas background = new Canvas(screenWidth, screenHeight);
    GraphicsContext gcBg = background.getGraphicsContext2D();
    Canvas shadows = new Canvas(screenWidth, screenHeight);
    GraphicsContext gcShadows = shadows.getGraphicsContext2D();
    Canvas collisions = new Canvas(screenWidth, screenHeight);
    GraphicsContext gcCollisions = collisions.getGraphicsContext2D();

    Canvas foreground = new Canvas(screenWidth, screenHeight);
    GraphicsContext gcFg = foreground.getGraphicsContext2D();
    //Default Player Position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 1 * scale;
    int playerSize = 25;
    int animationEcho = 7, animationEchoCounter = 0; //How many 'squares' left behind by player when player moves.
    LinkedList<Rectangle> animationEchoList = new LinkedList<Rectangle>();
    //Collision List
    ArrayList<Line> coords = new ArrayList<Line>();

    //Input
    KeyHandler keyHandler = new KeyHandler();

    public GamePanel() {
        foreground.setFocusTraversable(true);
        foreground.addEventHandler(KeyEvent.ANY, keyHandler);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent eh) {
                System.out.println(eh.getX() + " " + eh.getY());
            }
        });
        this.getChildren().addAll(background, shadows, foreground, collisions);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        gcFg.setFill(Paint.valueOf("black"));
        drawCollisions(gcCollisions);
        drawBackground(gcBg);
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            //Delta Loop
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                //Update Information
                update();
                //Draw 
                drawPlayerPast(gcShadows);
                drawPlayerCurrent(gcFg);
                //Clear
                //
                delta--;
            }
        }
    }

    public void update() {

        if (keyHandler.upPressed) {
            if (checkForCollision(playerY -= playerSpeed, playerX)) {
                playerY -= playerSpeed;
            } else {
                playerY += playerSpeed + 1;
            }
        }
        if (keyHandler.downPressed) {
            if (checkForCollision(playerY += playerSpeed, playerX)) {
                playerY += playerSpeed;
            } else {
                playerY -= playerSpeed + 1;
            }
        }
        if (keyHandler.leftPressed) {
            if (checkForCollision(playerY, playerX -= playerSpeed)) {
                playerX -= playerSpeed;
            } else {
                playerX += playerSpeed + 1;
            }
        }
        if (keyHandler.rightPressed) {
            if (checkForCollision(playerY, playerX += playerSpeed)) {
                playerX += playerSpeed;
            } else {
                playerX -= playerSpeed + 1;
            }
        }

    }

    public void drawPlayerCurrent(GraphicsContext gc) {
        gcFg.clearRect(0, 0, screenWidth, screenHeight);

        gc.setFill(Paint.valueOf("blue"));
        gc.setGlobalAlpha(1);

        gc.fillRect(playerX, playerY, playerSize, playerSize);
    }

    private void drawPlayerPast(GraphicsContext gc) {
        gc.setGlobalAlpha(0.1);
        gc.setFill(Paint.valueOf("black"));
        gc.fillRect(playerX, playerY, playerSize, playerSize);

        animationEchoCounter++;
        if (animationEchoCounter >= animationEcho) {
            gc.clearRect(0, 0, screenWidth, screenHeight);
            animationEchoCounter = 0;
        }
    }

    private void drawBackground(GraphicsContext gc) {
        FileInputStream file = null;
        try {
            file = new FileInputStream("C:/Users/14048/Desktop/LevelDesign.jpg");
            Image img = new Image(file);
            gc.drawImage(img, 0, 0, screenWidth, screenHeight);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void drawCollisions(GraphicsContext gc) {
        //Unique to every 'background'
        gc.setStroke(Paint.valueOf("red"));
        gc.setLineWidth(10);
        gc.strokeLine(198, 230, 690, 230);
        Line temp = new Line(230, 198, 230, 690);
        temp.setStrokeWidth(10);
        coords.add(temp);

        gc.strokeLine(690, 230, 690, 345);
        temp = new Line(230, 690, 345, 690);
        temp.setStrokeWidth(15);
        coords.add(temp);

    }

    private boolean checkForCollision(int playerY, int playerX) {

        for (Line temp : coords) {
            if (temp.getBoundsInParent().intersects(playerY, playerX, playerSize, playerSize)) {
                return false;
            }
        }
//        if ((playerY > 200 && playerY < 240) && (playerX > 190 && playerX < 700)) {
//            System.out.println("hi");
//            return -1;
//        }
        return true;
    }

}
