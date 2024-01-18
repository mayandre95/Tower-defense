package Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.GameWindow;

public class EditingWindow extends AWindow {
    private GameWindow gameWindow;
    private JButton buttonMenu;

    public EditingWindow(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;
        this.buttonMenu = new JButton("Menu");
        initializeButtons();
        changePanel();
    }

    @Override
    public void draw(Graphics G) {
        G.setColor(Color.RED);
        G.fillRect(15, 15, 50, 50);
    }

    @Override
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
                            ;
                            break;
                    }
                }
            });
        }
    }

}
