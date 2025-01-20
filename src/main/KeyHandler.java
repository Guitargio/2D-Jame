package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                System.out.println("up");
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                System.out.println("down");
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                System.out.println("left");
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                System.out.println("right");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
