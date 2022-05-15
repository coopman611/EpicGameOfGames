/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.gameattempt;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 *
 * @author 14048
 */
public class Enemy extends Sprite {

    public Enemy(int x, int y, String type, Color color) {
        super(x, y, type, color);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        timer.start();
        setSpeed(1);
    }

    boolean goLeft = true;
    boolean goUp = true;
    double screenX = Screen.getPrimary().getBounds().getWidth();
    double screenY = Screen.getPrimary().getBounds().getHeight();

    private void update() {
        System.out.println(screenX + " " + screenY);
        //ENEMY AI
        if (this.getX() > 0 && goLeft) {
            this.moveLeft();
        } else {
            goLeft = false;
        }

        if (this.getX() < screenX - 25 && !goLeft) {
            this.moveRight();
        } else {
            goLeft = true;
        }

        if (this.getY() > 0 && goUp) {
            this.moveUp();
        } else {
            goUp = false;
        }

        if (this.getY() < screenY - 50 && !goUp) {
            this.moveDown();
        } else {
            goUp = true;
        }

        // Collisions
        for (Sprite x : collisions) {
            if (x != this) {
                if (x.intersects(this.getBoundsInParent())) {
                    goLeft = !goLeft;
                    goUp = !goUp;
                }
            }
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
