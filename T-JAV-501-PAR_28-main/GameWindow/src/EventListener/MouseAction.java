package EventListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GameWindow;

public class MouseAction implements MouseListener, MouseMotionListener {
    private GameWindow gameWindow;

    public MouseAction(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at: (" + e.getX() + ", " + e.getY() + ")");

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

}
