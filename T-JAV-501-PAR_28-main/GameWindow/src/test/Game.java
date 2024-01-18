package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JFrame {

    private static final int SPRITE_WIDTH = 64;
    private static final int SPRITE_HEIGHT = 64;

    private Image spriteImage;
    private Timer timer;
    private int spriteX = 0; // Variable pour suivre la position horizontale des sprites

    public Game() {
        setTitle("Sprite Atlas Adjust Size Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Load your specific sprite to be repeated
            spriteImage = ImageIO.read(new File("GameWindow/resources/SpriteMap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up a timer to trigger repaint
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mettre à jour la position horizontale des sprites
                spriteX += 10;
                repaint(); // Trigger a repaint to update the displayed sprites
            }
        });

        timer.start();

        // Create a custom JPanel for drawing
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawRepeatedSprites(g);
            }
        };

        // Add a component listener to detect size changes
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint(); // Trigger a repaint when the panel size changes
            }
        });

        // Add the panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    private void drawRepeatedSprites(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calculate the number of sprites needed to fill the screen
        int horizontalCount = panelWidth / SPRITE_WIDTH + 1;
        int verticalCount = panelHeight / SPRITE_HEIGHT + 1;

        // Draw the sprites to fill the screen with animation
        for (int i = 0; i < horizontalCount; i++) {
            for (int j = 0; j < verticalCount; j++) {
                int x = spriteX % SPRITE_WIDTH; // Utilisez la position horizontale actuelle avec la largeur du sprite
                int y = 128;
                int width = SPRITE_WIDTH;
                int height = SPRITE_HEIGHT;

                BufferedImage subimage = ((BufferedImage) spriteImage).getSubimage(x, y, width, height);
                g.drawImage(subimage, i * SPRITE_WIDTH, j * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, null);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
            }
        });
    }
}
