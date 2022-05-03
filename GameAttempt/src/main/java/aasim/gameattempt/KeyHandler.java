/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.gameattempt;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author 14048
 */
public class KeyHandler implements EventHandler<KeyEvent> {

    public boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false, shiftPressed = false, keyPressed = false;

    @Override
    public void handle(KeyEvent t) {
        if (t.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            switch (t.getCode()) {
                case W:
                    upPressed = true;
                    break;
                case S:
                    downPressed = true;
                    break;
                case A:
                    leftPressed = true;
                    break;
                case D:
                    rightPressed = true;
                    break;
                case SHIFT:
                    shiftPressed = true;
                    break;
            }
            keyPressed = true;
        }
        if (t.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            switch (t.getCode()) {
                case W:
                    upPressed = false;
                    break;
                case S:
                    downPressed = false;
                    break;
                case A:
                    leftPressed = false;
                    break;
                case D:
                    rightPressed = false;
                    break;
                case SHIFT:
                    shiftPressed = false;
                    break;
            }
            keyPressed = false;
        }
    }
}
