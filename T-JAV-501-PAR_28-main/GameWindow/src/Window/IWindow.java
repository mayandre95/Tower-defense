package Window;

import java.awt.Graphics;

import javax.swing.JButton;

public interface IWindow {
    void draw(Graphics G);

    void setUpButton(JButton b, int x, int y, int w, int h);

    void changePanel();

    void initializeButtons();

    void mouseClicked();
}
