package main;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

import Enemies.AEnemy;
import Enemies.Gobelin;
import Window.AWindow;
import Window.CommandWindow;
import Window.IWindow;
import Window.MenuWindow;
import Window.PlayingWindow;
import Window.EditingWindow;

public class GameWindow extends JFrame {
    private AWindow currentFrame;
    private MenuWindow menu;
    private PlayingWindow playing;
    private CommandWindow command;
    private EditingWindow editing;
    // private static int refreshBySec = 0;
    // private static int updateBySec = 0;
    private Timer timer;
    private Timer secondTimer;
    private Timer update;

    private int refreshBySecMenu;
    private int refreshBySecPlaying;
    private int refreshBySecCommand;
    private int refreshBySecEditing;
    private int updateBySecMenu;
    private int updateBySecPlaying;
    private int updateBySecCommand;
    private int updateBySecEditing;

    public <T extends AWindow> void LauncherWindow(T currentFrame) {
        SwingUtilities.invokeLater(() -> {
            Dimension initialSize = new Dimension(1930, 1050);
            setMinimumSize(initialSize);
            setMaximumSize(initialSize);
            setPreferredSize(initialSize);

            getContentPane().removeAll();
            getContentPane().add((Component) currentFrame);
            refreshFrame();

            setResizable(true); // Allow resizing
            pack(); // Sizes the frame based on the preferred size of its contents
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Centers the frame on the screen
            setVisible(true);

            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
            if (update != null && update.isRunning()) {
                update.stop();
            }
            if (secondTimer != null && secondTimer.isRunning()) {
                secondTimer.stop();
            }

            timer = new Timer(16, e -> {
                GameWindow.this.repaint();
                refreshFrame();
                if (currentFrame instanceof PlayingWindow) {
                    refreshBySecPlaying++;
                } else if (currentFrame instanceof EditingWindow) {
                    refreshBySecEditing++;
                } else if (currentFrame instanceof CommandWindow) {
                    refreshBySecCommand++;
                } else {
                    refreshBySecMenu++;
                }
            });

            update = new Timer(9, e -> {
                refreshFrame();
                if (currentFrame instanceof PlayingWindow) {
                    playing.update();
                    updateBySecPlaying++;
                } else if (currentFrame instanceof EditingWindow) {
                    updateBySecEditing++;
                } else if (currentFrame instanceof CommandWindow) {
                    updateBySecCommand++;
                } else {
                    updateBySecMenu++;
                }
            });

            secondTimer = new Timer(1000, e -> {
                if (currentFrame instanceof PlayingWindow) {
                    System.out.println("Nombre de frame par seconde (PlayingWindow): " + refreshBySecPlaying
                            + "|| update : " + updateBySecPlaying);
                    refreshBySecPlaying = 0;
                    updateBySecPlaying = 0;
                } else if (currentFrame instanceof EditingWindow) {
                    System.out.println("Nombre de frame par seconde (EditingWindow): " + refreshBySecEditing
                            + "|| update : " + updateBySecEditing);
                    refreshBySecEditing = 0;
                    updateBySecEditing = 0;
                } else if (currentFrame instanceof CommandWindow) {
                    System.out.println("Nombre de frame par seconde (CommandWindow): " + refreshBySecCommand
                            + "|| update : " + updateBySecCommand);
                    refreshBySecCommand = 0;
                    updateBySecCommand = 0;
                } else {
                    System.out.println("Nombre de frame par seconde (MenuWindow): " + refreshBySecMenu + "|| update : "
                            + updateBySecMenu);
                    refreshBySecMenu = 0;
                    updateBySecMenu = 0;
                }
            });

            timer.start();
            update.start();
            secondTimer.start();

        });
    }

    public GameWindow() {
        this.menu = new MenuWindow(this);
        this.playing = new PlayingWindow(this);
        this.editing = new EditingWindow(this);
        this.command = new CommandWindow(this);
        currentFrame = menu;
        LauncherWindow(currentFrame);
        System.out.println("" + playing.map.getIndexRoad());
    }

    public void refreshFrame() {
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
    }

    public IWindow getCurrentFrame() {
        return currentFrame;
    }

    public MenuWindow getMenu() {
        return menu;
    }

    public <T extends AWindow> void setCurrentFrame(T currentFrame) {
        this.currentFrame = currentFrame;
    }

    public PlayingWindow getPlaying() {
        return playing;
    }

    public CommandWindow getCommand() {
        return command;
    }

    public EditingWindow getEditing() {
        return editing;
    }

}