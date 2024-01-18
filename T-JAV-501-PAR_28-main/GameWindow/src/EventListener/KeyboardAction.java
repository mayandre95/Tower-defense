package EventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GameWindow;

public class KeyboardAction implements KeyListener {
    private GameWindow gameWindow;

    public KeyboardAction(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }
}
