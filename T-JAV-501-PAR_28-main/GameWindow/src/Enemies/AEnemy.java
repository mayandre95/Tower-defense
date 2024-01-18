package Enemies;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.Timer;

import Interface.IEntity;
import Manager.SpriteManager;
import Window.PlayingWindow;

public abstract class AEnemy implements IEnemy, IEntity {
    protected float pv;
    protected float speed = 0.5f;
    protected float damage;
    protected final float maxPv;
    protected int xSprite = 0;
    protected int ySprite;
    protected int level = 1;
    protected int coin;
    protected float xStart = 1 * 64;
    protected float yStart = 13 * 64;
    protected float xCoord = xStart;
    protected float yCoord = yStart;
    protected boolean alive = true;
    protected PlayingWindow play;
    protected boolean castle = false;
    protected static ArrayList<AEnemy> listEnemies = new ArrayList<>();
    protected String dir = "NULL";

    protected int currentSpriteIndex = 0;
    protected SpriteManager<AEnemy> spriteManager;
    protected Image currentFrame;

    public AEnemy(float speed, float pv, float damage, int y, int coin, PlayingWindow playingWindow) {
        this.pv = pv;
        this.ySprite = y;
        this.damage = damage;
        this.maxPv = pv;
        this.coin = coin;
        this.speed = speed;
        this.play = playingWindow;
        this.spriteManager = new SpriteManager<>(this);
        currentFrame = spriteManager.getSpriteImage()[currentSpriteIndex];
        animateCharacter();
        Timer animationTimer = new Timer(200, e -> animateCharacter());
        animationTimer.start();
    }

    public void upgradePv(int newPv) {
        this.pv = this.pv + newPv;
    }

    @Override
    public void upgradeDamage(float newDamage) {
        this.damage += newDamage;
    }

    public void getDamage(float damage) {
        pv -= damage;
        System.out.println("enemie touché");
        if (pv <= 0) {
            alive = false;
            //effacer l'ennemie
        }
    }
    

    private void animateCharacter() {

        if (currentSpriteIndex > spriteManager.getSpriteImage().length - 1) {
            currentSpriteIndex = 0;
        }
        currentFrame = spriteManager.getSpriteImage()[currentSpriteIndex];
        currentSpriteIndex++;
    }

    public void updatePosition() {
        if (xCoord < 282 && yCoord == 832) {

            if (xCoord + speed > 282) {
                xCoord = 282;
            } else {
                xCoord += speed;
            }
        }

        else if (xCoord == 282 && yCoord > 218) {

            if (yCoord - speed < 218) {
                yCoord = 218;
            } else {
                yCoord -= speed;
            }
        }

        else if (xCoord < 1184 && yCoord == 218) {

            if (xCoord + speed > 1184) {
                xCoord = 1184;
            } else {
                xCoord += speed;
            }
        }

        else if (xCoord == 1184 && yCoord < 815) {

            if (yCoord + speed > 815) {
                yCoord = 815;
            } else {
                yCoord += speed;
            }
        }

        else if (xCoord < 1535 && yCoord == 815) {

            if (xCoord + speed > 1535) {
                xCoord = 1535;
            } else {
                xCoord += speed;
            }
        }

        if (xCoord == 1535 && yCoord == 815) {
            System.out.println("Game Over");
        }
    }

    private boolean ObstacleManager(int newX, int newY) {

        System.out.println("The x of the new position is " + newX);
        System.out.println("The y of the new position is " + newY);
        int xMap = newX / 64;
        int yMap = newY / 64;

        int index = play.map.getMatrice()[xMap][yMap];

        if (play.map.getIndexEnd() == index) {
            setCastle(true);
            return false;
        } else if (play.map.getIndexRoad().contains(index)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void move() {

    }

    // Methode Abstract
    public abstract void upgradeLvl1();

    public abstract void upgradeLvl2();

    // Getter et Setter
    public final float getPV() {
        return pv;
    }

    public final float getSpeed() {
        return speed;
    }

    @Override
    public final float getDamage() {
        return damage;
    }

    public final void setDamage(int damage) {
        this.damage = damage;
    }

    public final float getMaxPv() {
        return maxPv;
    }

    public final void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public final int getLevel() {

        return this.level;
    }

    @Override
    public final float getPv() {
        return this.pv;
    }

    @Override
    public final void setDamage(float damage) {
        this.damage = damage;

    }

    @Override
    public int getCoin() {
        return this.coin;
    }

    public int getxSprite() {
        return xSprite;
    }

    public int getySprite() {
        return ySprite;
    }

    public Image getCurrentFrame() {
        return currentFrame;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public float getxStart() {
        return xStart;
    }

    public float getyStart() {
        return yStart;
    }

    // public void setXandYStart(int xStart, int yStart) {
    // this.xStart = xStart;
    // this.yStart = yStart;
    // }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getxCoord() {
        return xCoord;
    }

    public float getyCoord() {
        return yCoord;
    }

    public boolean isCastle() {
        return castle;
    }

    public void setCastle(boolean castle) {
        this.castle = castle;
    }

    public static ArrayList<AEnemy> getListEnemies() {
        return listEnemies;
    }

}
