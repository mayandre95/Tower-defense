package Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import main.GameWindow;

public class CommandWindow extends AWindow {
    private GameWindow gameWindow;
    private JButton buttonMenu;
    private JLabel titleLabel;

    public CommandWindow(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;
        setLayout(null);
        this.buttonMenu = new JButton("Menu");
        initializeButtons();
        changePanel();

        this.titleLabel = new JLabel("Welcome to the command window");
        Font titleFont = new Font("Arial", Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(1250 / 2 - 200, 20, 800, 30);
        add(titleLabel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 18));

        String additionalText = "What is the aim of the game?";
        g.drawString(additionalText, 50, 230);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 18));

        String additional = "Castle Clash is a tower defence game. You play as a general whose aim is to protect the King's castle from the monsters invading the kingdom. You'll come face to face with monsters of\nall kinds, including dragons, goblins and trolls. You must place soldiers in specific areas. You can place different types of soldiers, such as archers, mages and wizards.";

        String[] lines = additional.split("\n");

        int y = 260;

        for (String line : lines) {
            g.drawString(line, 50, y);
            y += 20;
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 18));

        String additional2 = "You can also create your own map using the Edit button. Enjoy!";
        g.drawString(additional2, 50, 310);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 50, 50);
    }

    public void initializeButtons() {
        setUpButton(buttonMenu, 15, 15, 100, 30);
    }

    @Override
    public void changePanel() {
        for (JButton b : this.getButtonList()) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (b.getText()) {
                        case "Menu":
                            gameWindow.setCurrentFrame(gameWindow.getMenu());
                            gameWindow.LauncherWindow(gameWindow.getMenu());
                            break;
                    }
                }
            });
        }
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

}