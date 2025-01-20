package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("res/player/boy_down_2.png"));
            left1 = ImageIO.read(new File("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("res/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("res/player/boy_right_2.png"));
        } catch ( IOException e ) {
            System.out.println("Errore nel caricamento di un immagine");
        }
    }



    public void update() {
        if(keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        }
        else if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
        }
        else if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        }
        else if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }

        spriteCounter++;
        if( spriteCounter > 10 ) {
            if( spriteNum == 1 ) spriteNum = 2;
            else if ( spriteNum == 2 ) spriteNum = 1;

            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.WHITE);
        //g2.fillRect( x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if( spriteNum == 1 ) image = up1;
                else image = up2;
                break;
            case "down":
                if( spriteNum == 1 ) image = down1;
                else image = down2;
                break;
            case "left":
                if( spriteNum == 1 ) image = left1;
                else image = left2;
                break;
            case "right":
                if( spriteNum == 1 ) image = right1;
                else image = right2;
                break;
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null );
    }
}
