/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.gameattempt;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 14048
 */
public class Wall extends Sprite {

    Rectangle hitbox;

    public Wall(int x, int y, int w, int h, String type, Color color) {
        super(x, y, "wall", color);
        
        Sprite.collisions.add(this);
    }

}
