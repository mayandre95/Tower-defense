package Manager;

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
            case "orc":
                sprite = imgSprites.getSubimage(0 * 32, 1 * 32, 32, 32);
                break;
            case "bat":
                sprite = imgSprites.getSubimage(1 * 32, 1 * 32, 32, 32);
                break;
            case "wolf":
                sprite = imgSprites.getSubimage(2 * 32, 1 * 32, 32, 32);
                break;
            case "knight":
                sprite = imgSprites.getSubimage(3 * 32, 1 * 32, 32, 32);
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
                "/home/axelradin/PreMsc/repository/java_repository/T-JAV-501-PAR_28/TDF/res/spriteatlas.png");
        try {
            imgSprites = ImageIO.read(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
