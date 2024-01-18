package Towers;

import java.awt.Image;
import java.util.ArrayList;

import Enemies.AEnemy;
import Enemies.IEnemy;
import Interface.IEntity;
import Manager.SpriteManager;
import Window.PlayingWindow;

public abstract class ATower implements IEntity, ITower {
    protected float damage;
    protected int range;
    protected int targetNumber;
    protected int xSprite;
    protected int ySprite = 5;
    protected float xCoord;
    protected float yCoord;
    protected Image image;

    protected int level = 1;
    protected PlayingWindow play;
    protected SpriteManager<ATower> spriteManager;

    public ATower(float damage, int range, int level, int targetNumber, int xSprite, PlayingWindow playingWindow) {
        this.damage = damage;
        this.range = range;
        this.level = level;
        this.xSprite = xSprite;
        this.spriteManager = new SpriteManager<ATower>(this);
        this.image = spriteManager.getSpriteImage()[0];
        this.play = playingWindow;
        this.targetNumber = targetNumber;

    }

    public void attackEnemies(ArrayList<AEnemy> enemies) {
        for (AEnemy enemy : enemies) {
            // Vérifiez si l'ennemi est à portée
            if (isEnemyInRange(enemy)) {
                // Infligez des dégâts à l'ennemi
                enemy.getDamage(damage);
            }
        }
    }

    private boolean isEnemyInRange(AEnemy enemy) {
        double distance = calculateDistance(enemy.getxCoord(), enemy.getyCoord());
        System.out.println("dist " + distance + " range " + range);
        return distance <= range;

    }

    private double calculateDistance(float targetX, float targetY) {
        double deltaX = Math.abs(xCoord - targetX);
        double deltaY = Math.abs(yCoord - targetY);
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public void upgradeDamage(float damage) { // a revoir
        this.damage += damage;
    }

    @Override
    public void upgradeRange(int range) { // a revoir
        this.range += range;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    @Override
    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public float getDamage() {
        return damage;
    }

    @Override
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public int getRange() {
        return range;
    }

    public final int getxSprite() {
        return xSprite;
    }

    public final int getySprite() {
        return ySprite;
    }

    public abstract void upgradeLvl2();

    public abstract void upgradeLvl3();

    // pour déterminer si la toure peut être placée
    public Image getSprite() {
        return null;
    }

    public float getxCoord() {
        return xCoord;
    }

    public float getyCoord() {
        return yCoord;
    }

    public Image getImage() {
        return image;
    }

}
