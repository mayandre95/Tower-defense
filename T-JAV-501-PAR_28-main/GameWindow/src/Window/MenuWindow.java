package Window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.GameWindow;

public class MenuWindow extends AWindow {
    private JButton buttonPlaying;
    private JButton buttonCommand;
    private JButton buttonEditing;
    private JButton buttonQuit;
    private GameWindow gameWindow;

    public MenuWindow(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;
        setLayout(null);
        this.buttonPlaying = new JButton("Playing");
        this.buttonCommand = new JButton("Command");
        this.buttonEditing = new JButton("Editing");
        this.buttonQuit = new JButton("Quit");
        initializeButtons();
        changePanel();
    }

    @Override
    public void initializeButtons() {
        int yIncr = 50;
        int w = 120;
        int x = 800 / 2 - (w / 2);
        int h = w / 3;

        setUpButton(buttonPlaying, x, yIncr, w, h);
        setUpButton(buttonCommand, x, yIncr * 2, w, h);
        setUpButton(buttonEditing, x, yIncr * 3, w, h);
        setUpButton(buttonQuit, x, yIncr * 4, w, h);
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        draw(G);
    }

    @Override
    public void draw(Graphics G) {
    }

    public void drawButton() {

    }

    @Override
    public void changePanel() {
        for (JButton b : this.getButtonList()) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (b.getText()) {
                        case "Playing":
                            gameWindow.setCurrentFrame(gameWindow.getPlaying());
                            gameWindow.LauncherWindow(gameWindow.getPlaying());
                            ;
                            break;
                        case "Command":
                            gameWindow.setCurrentFrame(gameWindow.getCommand());
                            gameWindow.LauncherWindow(gameWindow.getCommand());
                            break;
                        case "Editing":
                            gameWindow.setCurrentFrame(gameWindow.getEditing());
                            gameWindow.LauncherWindow(gameWindow.getEditing());
                            break;
                        case "Quit":
                            System.exit(0);
                            break;

                        default:
                            break;
                    }
                    ;
                }
            });
        }
    }

}
