package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Move extends JFrame {

    private JLabel characterLabel;
    private Image[] characterFrames;
    private int currentFrameIndex = 0; 
    private int xPosition = 50; // Initial X position
    private int yPosition = 50; // Initial Y position

    public Move() {
        setTitle("Character Animation");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the sprite atlas and split it into sub-images
        characterFrames = loadCharacterFrames();

        // Create a JLabel to display the character
        characterLabel = new JLabel(new ImageIcon(characterFrames[currentFrameIndex]));
        characterLabel.setBounds(xPosition, yPosition, 64, 64); // Set initial position and size
        add(characterLabel);

        // Create a timer to animate and move the character
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animateCharacter();
                moveCharacter();
                currentFrameIndex++;
            }
        });
        timer.start();

        setVisible(true);
    }

    private Image[] loadCharacterFrames() {
        try {
            ImageIcon spriteAtlas = new ImageIcon("/home/andre/T-JAV-501-PAR_28/Bootstrap1/res/SpriteCharacters.png");

            // Convert the ToolkitImage to BufferedImage
            Image toolkitImage = spriteAtlas.getImage();
            BufferedImage bufferedImage = new BufferedImage(
                    toolkitImage.getWidth(null),
                    toolkitImage.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB
            );
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(toolkitImage, 0, 0, null);
            g.dispose();

            // Split the sprite atlas into sub-images
            int frameWidth = 64; // Width of a frame
            int frameHeight = 64; // Height of a frame
            int numFrames = 9; // Number of frames in the sprite atlas

            Image[] frames = new Image[numFrames];

            for (int i = 0; i < numFrames; i++) {
                frames[i] = bufferedImage.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
            }

            return frames;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("personnage non trouvé");
            return null;
        }
    }

    private void animateCharacter() {
        // Update the current frame index
        if (currentFrameIndex > characterFrames.length - 1) {
            currentFrameIndex = 0;
        }
        characterLabel.setIcon(new ImageIcon(characterFrames[currentFrameIndex]));
    }

    private void moveCharacter() {
        // Update the position of the character
        xPosition += 5; // Adjust the movement speed as needed
        characterLabel.setBounds(xPosition, yPosition, 64, 64); // Update the position
    }

  

    public static void draw(Graphics g) {
    }
}
