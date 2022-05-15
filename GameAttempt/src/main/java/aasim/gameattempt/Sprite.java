/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.gameattempt;

import java.util.ArrayList;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 14048
 */
public class Sprite extends Rectangle {

    boolean dead = false;
    final String type;
    int speed = 20;
    public static ArrayList<Sprite> collisions = new ArrayList<>();
    public static ArrayList<Sprite> players = new ArrayList<Sprite>();
    
    Sprite(int x, int y, int w, int h, String type, Color color) {
        super(w, h, color);
        
        collisions.add(this);
        this.type = type;
        setX(x);
        setY(y);
    }

    void moveLeft() {
        setX(getX() - speed);
    }

    void moveRight() {
        setX(getX() + speed);
    }

    void moveUp() {
        setY(getY() - speed);
    }

    void moveDown() {
        setY(getY() + speed);
    }
}
