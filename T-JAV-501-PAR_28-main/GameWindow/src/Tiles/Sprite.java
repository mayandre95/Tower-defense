package Tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
    private BufferedImage imgSprites;
    private String type;

    public Sprite(String type) {
        this.type = type.toLowerCase();
        ImportImg();
    }

    public BufferedImage getSprite() {
        ImportImg();
        BufferedImage sprite = null;
        switch (type) {
            case "goblin":
                sprite = imgSprites.getSubimage(0 * 32, 0 * 32, 32, 32);
                break;
            case "dragon":
                sprite = imgSprites.getSubimage(0 * 32, 1 * 32, 32, 32);
                break;
            case "troll":
                sprite = imgSprites.getSubimage(0 * 32, 2 * 32, 32, 32);
                break;
            default:
                sprite = null;
                break;
        }
        return sprite;

    }

    public void draw(Graphics G, int x, int y) {
        if (getSprite() != null) {
            G.drawImage(getSprite(), 32 * x, 32 * y, null);
        }
    }

    private void ImportImg() {

        File sprite = new File(
                "/home/andre/T-JAV-501-PAR_28/Bootstrap1/res/SpriteCharacters.png");
        try {
            imgSprites = ImageIO.read(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
