package main;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import Towers.ATower;

public class Map extends JPanel {
    private BufferedImage spriteAtlas;
    private BufferedImage towerAtlas;
    private ArrayList<BufferedImage> spriteMap;
    private ArrayList<BufferedImage> towerMap;
    private ArrayList<Integer> indexRoad;
    private int indexEnd = 41;
    private int[][] matrice;
    private int[][] _tower;
    // private ArrayList<T> towers;

    public Map() {
        // initialise les variables
        this.spriteMap = new ArrayList<>();
        this.indexRoad = new ArrayList<>();
        this.towerMap = new ArrayList<>();
        this.matrice = matriceMap();
        // this._tower = matriceTower();
        // appelle la fonction

        Sprites();
        roadList();
        tower();

    }

    public void Sprites() {
        // Charger le sprite atlas puis découper les sprites individuels
        try {
            spriteAtlas = ImageIO.read(new File("GameWindow/resources/SpriteMap.png"));
            if (spriteAtlas != null) {
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 10; x++) {
                        spriteMap.add(spriteAtlas.getSubimage(x * 64, y * 64, 64, 64));
                    }
                }
                spriteMap.add(spriteAtlas.getSubimage(0 * 64, 4 * 64, 128, 192));
                spriteMap.add(spriteAtlas.getSubimage(2 * 64, 4 * 64, 192, 192));
            } else {
                System.out.println("Impossible de charger l'image");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[][] matriceMap() {
        int[][] matrice = {
                { 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
                        11 },
                { 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
                        11 },
                { 11, 11, 11, 21, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 11, 11, 11, 11, 11,
                        11 },
                { 11, 11, 11, 19, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 17, 11, 11, 11, 11, 11,
                        11 },
                { 11, 11, 11, 19, 30, 30, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 30, 30, 17, 11, 11, 11, 11, 11,
                        11 },
                { 11, 11, 11, 19, 30, 17, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 19, 30, 17, 11, 11, 11, 11, 11,
                        11 },
                { 11, 11, 11, 19, 30, 17, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 19, 30, 17, 11, 11, 11, 11, 11,
                        11 },
                { 11, 11, 11, 19, 30, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 19, 30, 17, 11, 11, 11, 11, 11, 11 },
                { 11, 11, 11, 19, 30, 17, 1, 1, 1, 1, 1, 1, 1, 34, 1, 1, 1, 19, 30, 17, 11, 11, 11, 11, 11, 11 },
                { 11, 11, 11, 19, 30, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 19, 30, 17, 11, 11, 11, 11, 11, 11 },
                { 11, 11, 11, 19, 30, 17, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 19, 30, 17, 11, 11, 11, 11, 11,
                        11 },
                { 40, 39, 11, 19, 30, 17, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 19, 30, 30, 16, 16, 16, 41, 39,
                        39 },
                { 39, 39, 30, 30, 30, 17, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 19, 30, 30, 30, 30, 30, 39, 39,
                        39 },
                { 39, 39, 18, 18, 18, 23, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 22, 18, 18, 18, 18, 18, 39, 39,
                        39 },
                { 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
                        11 },
        };
        return matrice;

    }

    public void tower() {
        // Charger le sprite atlas puis découper les sprites individuels
        try {
            towerAtlas = ImageIO.read(new File("GameWindow/resources/SpriteCharacters.png"));
            if (towerAtlas != null) {
                for (int y = 5; y < 6; y++) {
                    for (int x = 0; x < 5; x++) {
                        towerMap.add(towerAtlas.getSubimage(x * 64, y * 64, 64, 64));
                    }
                }
            } else {
                System.out.println("Impossible de charger l'image");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // private int[][] matriceTower() {
    // int[][] _tower = {
    // { 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
    // 11, 11, 11, 11, 11, 11,
    // 11 },
    // { 11, 11, 11, 11, 11, 11, 11, 0, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
    // 11, 11, 11, 11, 11, 11,
    // 11 },
    // { 11, 11, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11,
    // 11, 11, 11 },
    // { 11, 11, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11,
    // 11, 11, 11 },
    // { 11, 11, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11,
    // 11, 11, 11 },
    // { 11, 11, 11, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 1, 4, 4, 4,
    // 11, 11, 11, 11, 11, 11 },
    // { 11, 11, 2, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4, 4, 4,
    // 11, 11, 11, 11, 11, 11 },
    // { 11, 11, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11,
    // 11, 11, 11 },
    // { 11, 11, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11,
    // 11, 11, 11 },
    // { 11, 11, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11,
    // 11, 11, 11 },
    // { 11, 11, 11, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4, 4, 4,
    // 11, 11, 11, 11, 11, 11 },
    // { 4, 4, 11, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4, 4, 4, 4,
    // 4, 4, 4, 4, 4 },
    // { 4, 4, 4, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 3, 4, 4, 4, 4, 4,
    // 4, 4, 4, 4 },
    // { 4, 4, 4, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 4, 4, 4, 4,
    // 4, 4, 4, 4, 4 },
    // { 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
    // 11, 11, 11, 11, 11, 11,
    // 11 },
    // };
    // return _tower;

    // }

    public void draw(Graphics g) {
        // Parcours de chaque ligne de la carte
        for (int i = 0; i < matrice.length; i++) {
            // Parcours de chaque colonne de la carte
            for (int j = 0; j < matrice[i].length; j++) {
                // Obtention de l'indice de tuile à cette position de la carte
                int indiceTuile = matrice[i][j];

                // Vérification si l'indice de tuile est valide
                if (indiceTuile >= 0 && indiceTuile < spriteMap.size()) {
                    // Vérification si l'indice de tuile est valide pour le tableau d'images
                    g.drawImage(spriteMap.get(indiceTuile), j * 64, i * 64, null);
                }
            }
        }
        // for (int i = 0; i < _tower.length; i++) {
        // // Parcours de chaque colonne de la carte
        // for (int j = 0; j < _tower[i].length; j++) {
        // // Obtention de l'indice de tuile à cette position de la carte
        // int indiceTower = _tower[i][j];

        // // Vérification si l'indice de Tower est valide
        // if (indiceTower >= 0 && indiceTower < towerMap.size()) {
        // // Vérification si l'indice de Tower est valide pour le tableau d'images
        // g.drawImage(towerMap.get(indiceTower), j * 64, i * 64, null);
        // }
        // }
        // }
    }

    public void roadList() {
        for (int x = 12; x < 31; x++) {
            indexRoad.add(x);
        }
        indexRoad.add(39);
        indexRoad.add(40);
    }

    public boolean isOnRoad(int pixelX, int pixelY) {
        // Calculate the array indices based on pixel coordinates
        int x = pixelX / 64;
        int y = pixelY / 64;

        // Check if the position is within the bounds of the matrice array
        if (x >= 0 && x < matrice[0].length && y >= 0 && y < matrice.length) {
            int tileIndex = matrice[y][x];
            return indexRoad.contains(tileIndex);
        } else {
            return false;
        }
    }

    public ArrayList<BufferedImage> getSpriteMap() {
        return spriteMap;
    }

    public ArrayList<BufferedImage> getTowerMap() {
        return towerMap;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public int[][] get_tower() {
        return _tower;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public ArrayList<Integer> getIndexRoad() {
        return indexRoad;
    }

}
