package Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.Map;

import javax.naming.ldap.SortKey;
import javax.swing.JButton;
import javax.swing.Timer;

import Enemies.AEnemy;
import Enemies.Dragon;
import Enemies.Gobelin;
import Enemies.JackOLentern;
import Enemies.SkullKing;
import Enemies.Troll;
import Manager.SpriteManager;
import Tiles.Tiles;
import Tiles.Tiles.ROAD_CORNER;
import Towers.ATower;
import Towers.Archer;
import Towers.Knight;
import Towers.Mage;
import Towers.Witch;
import main.GameWindow;

public class PlayingWindow extends AWindow implements MouseListener {
    private Random random = new Random();
    private GameWindow gameWindow;
    private JButton buttonMenu;
    private Tiles tile;
    private ArrayList<BufferedImage[]> tileList;
    public Map map;
    private Gobelin gob;
    private ArrayList<ATower> towerList = new ArrayList<>();
    private Archer arc = new Archer(64, 128, this);
    private SpriteManager<ATower> spr = new SpriteManager<ATower>(arc);
    private Timer drawTimerEnemies;

    // private ArrayList<AEnemy> listEnemies = new A
    public PlayingWindow(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;
        this.buttonMenu = new JButton("Menu");
        this.tile = new ROAD_CORNER();
        this.map = new Map();
        this.tileList = tile.tilesList();
        gob = new Gobelin(this);
        towerAdd();
        initializeButtons();
        enemiesAdd();
        changePanel();

        drawTimerEnemies = new Timer(600000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AEnemy.getListEnemies().clear();
                waveCreating(20);
            }
        });

        drawTimerEnemies.start();
    }

    public void drawHealthBar(Graphics g, AEnemy enemy) {
        float maxHealth = enemy.getMaxPv();
        float currentHealth = enemy.getPv();
        float healthPercentage = currentHealth / maxHealth;

        int barWidth = 40; // Largeur de la barre de vie
        int barHeight = 5; // Hauteur de la barre de vie
        int x = (int) enemy.getxCoord(); // Position X de l'ennemi
        int y = (int) enemy.getyCoord() - 10; // Position Y de l'ennemi (au-dessus)

        // Dessiner la barre de vie
        g.setColor(Color.RED);
        g.fillRect(x, y, barWidth, barHeight);

        // Dessiner la partie de la barre en fonction de la vie actuelle
        g.setColor(Color.GREEN); 
        g.fillRect(x, y, (int) (barWidth * healthPercentage), barHeight);
    }

    @Override
    public void draw(Graphics G) {
        map.draw(G);

        for (AEnemy enemy : AEnemy.getListEnemies()) {
            G.drawImage(enemy.getCurrentFrame(), (int) enemy.getxCoord(), (int) enemy.getyCoord(), null);
            drawHealthBar(G, enemy);
        }

        // G.drawImage(gob.getCurrentFrame(), (int) gob.getxCoord(), (int)
        // gob.getyCoord(), null);

        for (ATower t : towerList) {
            G.drawImage(t.getImage(), (int) t.getxCoord(), (int) t.getyCoord(), null);
        }

    }

    public void enemiesAdd() {

        Gobelin gob1 = new Gobelin(this);
        JackOLentern jack1 = new JackOLentern(this);
        Gobelin gob2 = new Gobelin(this);
        SkullKing skull2 = new SkullKing(this);
        JackOLentern jack3 = new JackOLentern(this);
        SkullKing skull1 = new SkullKing(this);
        Gobelin gob3 = new Gobelin(this);
        JackOLentern jack2 = new JackOLentern(this);
        Troll troll1 = new Troll(this);
        Troll troll2 = new Troll(this);
        Dragon dragon3 = new Dragon(this);
        SkullKing skull3 = new SkullKing(this);
        Dragon dragon1 = new Dragon(this);
        Troll troll3 = new Troll(this);
        Dragon dragon2 = new Dragon(this);
    }

    public void waveCreating(int enemiesNbr) {
        AEnemy.getListEnemies().clear();
        ArrayList<Class<? extends AEnemy>> enemyTypes = new ArrayList<>();
        enemyTypes.add(Dragon.class);
        enemyTypes.add(Gobelin.class);
        enemyTypes.add(JackOLentern.class);
        enemyTypes.add(Troll.class);
        enemyTypes.add(SkullKing.class);

        int numEnemies = random.nextInt(enemiesNbr) + 1; // Random number between 1 and 5

        for (int i = 0; i < numEnemies; i++) {
            Class<? extends AEnemy> randomEnemyType = enemyTypes.get(random.nextInt(enemyTypes.size()));
            try {
                AEnemy enemy = randomEnemyType.getDeclaredConstructor(PlayingWindow.class).newInstance(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void towerAdd() {
        towerList.add(new Archer(2 * 64, 6 * 64, this));
        towerList.add(new Witch(16 * 64, 5 * 64, this));
        towerList.add(new Mage(7 * 64, 1 * 64, this));
        towerList.add(new Knight(16 * 64, 12 * 64, this));
    }

    public void update() {
        
        for (ATower tower : towerList) {
            tower.attackEnemies(AEnemy.getListEnemies());
        }
        
        for (AEnemy enemy : AEnemy.getListEnemies()) {
            enemy.updatePosition();
        }
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("Mouse Clicked at X: " + mouseX + ", Y: " + mouseY);
        Rectangle bound = new Rectangle(e.getX(), e.getY(), 64, 64);
        // Add your logic here based on the mouse click coordinates
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
