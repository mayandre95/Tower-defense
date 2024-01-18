package Manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Enemies.AEnemy;
import Tiles.Tiles;
import Towers.ATower;
import static Tiles.Tiles.*;

public class SpriteManager<T> {
    private T spriteT;
    private BufferedImage spriteA;
    private BufferedImage[] spriteImage;

    public SpriteManager(T sprite) {
        this.spriteT = sprite;
        getSprite();
        getSpriteImg();

    }

    private void getSprite() {
        String spritePath = null;
        if (spriteT instanceof AEnemy || spriteT instanceof ATower) {
            spritePath = "GameWindow/resources/SpriteCharacters.png";
        } else {
            spritePath = "GameWindow/resources/SpriteMap2.png";
        }

        BufferedImage img = null;
        File file = new File(spritePath);
        try {
            img = ImageIO.read(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img == null) {
            System.out.println("The Tile Image  did not load");
        } else {
            this.spriteA = img;
        }

    }

    public BufferedImage getSpriteA() {
        return spriteA;
    }

    public BufferedImage[] getSpriteImage() {
        return spriteImage;
    }

    private void getSpriteImg() {
        if (spriteT instanceof ATower) {
            ATower tower = (ATower) spriteT;
            spriteImage = new BufferedImage[] {
                    spriteA.getSubimage(tower.getxSprite() * 64, tower.getySprite() * 64, 64, 64) };
        } else if (spriteT instanceof AEnemy) {
            AEnemy enemy = (AEnemy) spriteT;
            BufferedImage[] enemyImg = new BufferedImage[9];
            for (int x = enemy.getxSprite(); x < 9; x++) {
                enemyImg[x] = spriteA.getSubimage(x * 64, enemy.getySprite() * 64, 64, 64);
            }
            spriteImage = enemyImg;
        } else if (spriteT instanceof Tiles) {
            Tiles tile = (Tiles) spriteT;
            BufferedImage[] img1;
            if (tile.getType() != WATER) {
                img1 = new BufferedImage[] { spriteA.getSubimage(tile.getX() * 64, tile.getY() * 64, 64, 64) };
                if (tile.getRotation() > 0.0) {
                    BufferedImage rotImg1 = new BufferedImage(img1[0].getWidth(), img1[0].getHeight(),
                            img1[0].getType());
                    Graphics2D g2d = rotImg1.createGraphics();

                    g2d.rotate(Math.toRadians(tile.getRotation()), img1[0].getWidth() / 2, img1[0].getHeight() / 2);
                    g2d.drawImage(img1[0], 0, 0, null);
                    g2d.dispose();
                    img1 = new BufferedImage[] { rotImg1 };
                }
                spriteImage = img1;

            } else {
                img1 = new BufferedImage[2];
                for (int x = tile.getX(); x < tile.getX() + 2; x++) {
                    img1[x - 2] = spriteA.getSubimage(x * 64, tile.getY() * 64, 64, 64);
                }
            }
            BufferedImage[] sourceImg = new BufferedImage[0];
            BufferedImage[] sourceImg2 = new BufferedImage[0];
            BufferedImage[] ImgAnimated = new BufferedImage[0];
            if (tile.getX2() != null && tile.getY2() != null) {
                if (tile.isAnimated()) {
                    BufferedImage[] img2;
                    if (tile.getType() == WATER) {
                        img2 = new BufferedImage[] {
                                spriteA.getSubimage(tile.getX2() * 64, tile.getY2() * 64, 64, 64) };
                        sourceImg = img1;
                        sourceImg2 = img2;
                        ImgAnimated = new BufferedImage[img1.length];

                    } else if (tile.getType() == OBSTACLE) {
                        img2 = new BufferedImage[5];
                        for (int x = tile.getX2(); x < 5; x++) {
                            img2[x] = spriteA.getSubimage(tile.getX2() * 64, tile.getY2() * 64, 64, 64);
                        }
                        sourceImg = img2;
                        sourceImg2 = img1;
                        ImgAnimated = new BufferedImage[img2.length];

                    }
                    if (sourceImg.length > 0 && sourceImg2.length > 0 && ImgAnimated.length > 0) {

                        for (int i = 0; i < img1.length; i++) {
                            BufferedImage tempImg = new BufferedImage(sourceImg[0].getWidth(), sourceImg[0].getHeight(),
                                    sourceImg[0].getType());
                            Graphics2D g2d = tempImg.createGraphics();
                            g2d.drawImage(ImgAnimated[i], 0, 0, null);
                            g2d.rotate(Math.toRadians(tile.getRotation()), sourceImg[0].getWidth() / 2,
                                    sourceImg[0].getHeight() / 2);
                            g2d.drawImage(sourceImg2[0], 0, 0, null);
                            g2d.dispose();
                        }
                        spriteImage = ImgAnimated;
                    } else {
                        System.out.println("Image for animation and superposition empty");
                    }
                }
            } else {
                spriteImage = img1;
            }
        }

    }

    public BufferedImage Sprite() {
        return null;
    }

    public BufferedImage[] animatedSprite() {
        return null;
    }
}
