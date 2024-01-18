package Window;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import EventListener.KeyboardAction;
import EventListener.MouseAction;
import main.GameWindow;

public abstract class AWindow extends JPanel implements IWindow {
    private ArrayList<JButton> buttonList = new ArrayList<>();
    private MouseAction mouseAction;
    private KeyboardAction keyboardAction;
    private GameWindow gameWindow;

    public AWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        mouseAction = new MouseAction(gameWindow);
        keyboardAction = new KeyboardAction(gameWindow);
        addMouseListener(mouseAction);
        addMouseMotionListener(mouseAction);
        addKeyListener(keyboardAction);
        requestFocus();
    }

    // size = new Dimension(640, 800);
    // setMinimumSize(size);
    // setMaximumSize(size);
    // setPreferredSize(size);

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        draw(G);
    }

    @Override
    public void setUpButton(JButton b, int x, int y, int w, int h) {
        b.setBounds(x, y, w, h); // definir le bouton
        add(b); // ajoute le bouton au panel
        buttonList.add(b);
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    @Override
    public void mouseClicked() {

    }

}
