package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class test extends JFrame {

    private static final int SPRITE_WIDTH = 64;
    private static final int SPRITE_HEIGHT = 64;
    private static final int NUM_FRAMES = 10;  // Adjust based on the number of frames in your animated sprite

    private Image spriteAtlas;
    private int selectedSpriteIndex = 3;

    private Timer timer;

    public test() {
        setTitle("Animated Sprite Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        spriteAtlas = Toolkit.getDefaultToolkit().getImage("/home/andre/T-JAV-501-PAR_28/Bootstrap1/res/SpriteMap.png");

        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(spriteAtlas, 0);
        try {
            tracker.waitForID(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSpriteIndex = (selectedSpriteIndex + 1) % NUM_FRAMES;  // Switch to the next frame
                repaint();
            }
        });

        timer.start();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawAnimatedSprite(g);
            }
        };

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void drawAnimatedSprite(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int atlasWidth = spriteAtlas.getWidth(null);

        if (atlasWidth > 0) {
            int horizontalCount = (panelWidth + SPRITE_WIDTH - 1) / SPRITE_WIDTH;
            int verticalCount = (panelHeight + SPRITE_HEIGHT - 1) / SPRITE_HEIGHT;

            for (int i = 0; i < horizontalCount; i++) {
                for (int j = 0; j < verticalCount; j++) {
                    int spriteX = (selectedSpriteIndex % (atlasWidth / SPRITE_WIDTH)) * SPRITE_WIDTH;
                    int spriteY = (selectedSpriteIndex / (atlasWidth / SPRITE_WIDTH)) * SPRITE_HEIGHT;

                    g.drawImage(spriteAtlas, i * SPRITE_WIDTH, j * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT,
                            spriteX, spriteY, spriteX + SPRITE_WIDTH, spriteY + SPRITE_HEIGHT, null);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new test();
            }
        });
    }
}
/* 
public static class ImagePanel extends JPanel {
        BufferedImage towerSprite;
        BufferedImage enemySprite;
        SpriteManager<ATower> towerSpriteManager;
        SpriteManager<AEnemy> enemySpriteManager;
        SpriteManager<Tiles> tileSpriteManager;
        SpriteManager<Tiles> tileSpriteManager2;
        ROAD_CORNER roadCorner = new ROAD_CORNER();
        ROAD_MIDDLE roadMiddle = new ROAD_MIDDLE();

        public ImagePanel() {
            towerSpriteManager = new SpriteManager<>(new Archer());

            enemySpriteManager = new SpriteManager<>(new Dragon(this));

            tileSpriteManager = new SpriteManager<>(new ROAD_CORNER());

        }

        public static void main(String[] args) {
            ImagePanel panel = new ImagePanel();
            JFrame frame = new JFrame("Test tour image");
            frame.add(panel);
            frame.setSize(800, 800);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            // Add additional testing or assertions as needed
        }
    }
    */
