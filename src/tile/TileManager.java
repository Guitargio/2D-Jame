package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class TileManager {
    GamePanel gp;
    Tile[] tile;        // array con le 3 tile
    int[][] mapTileNum;     // matrice con la tilemap in numeri

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();     //Inizializza il vettore "tile" con le 3 tiles
        loadMap("/maps/tilemap.txt");          // Inizializza la matrice "mapTileNum"

    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        } catch (IOException e) {
            System.out.println("Errore nel caricamento di una tile");
        }
            catch (NullPointerException e) {
                System.out.println("ERRORE: una tile è null");
            }
    }

    public void loadMap(String filename) {
        try {
            mapTileNum = readMatrix(filename, gp.maxScreenRow, gp.maxScreenCol);
        } catch (IOException e) {
            System.out.println("Impossibile caricare la tilemap");
            throw new RuntimeException(e);
        }
    }

    public int[][] readMatrix(String filename, int rows, int cols) throws IOException {

        int[][] map = new int[rows][cols];

        try (Scanner input = new Scanner(getClass().getResourceAsStream(filename))){

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    map[i][j] = input.nextInt();
                    System.out.printf("%d", map[i][j]);
                }
                System.out.print("\n");
            }
            System.out.println("MAP SUCCESSFULLY LOADED");
        }
        return map;
    }

    public void draw(Graphics2D g2) {

        int x, y;
        x = y = 0;

        for(int i = 0; i < gp.maxScreenRow; i++) {
            for(int j = 0; j < gp.maxScreenCol; j++) {
                int tileType = mapTileNum[i][j];
                g2.drawImage(tile[tileType].image, x, y, gp.tileSize, gp.tileSize, null);
                x += gp.tileSize;
            }
            x = 0;
            y += gp.tileSize;
        }
    }
}
