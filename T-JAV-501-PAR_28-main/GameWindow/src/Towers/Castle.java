package Towers;

import Enemies.AEnemy;
import Window.PlayingWindow;

public class Castle extends ATower {

    protected float pv;
    protected final float maxPv;

    public Castle(float xCoord, float yCoord, PlayingWindow playingWindow) {
        super(5, 30, 1, 5, 5, playingWindow);
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.pv = 10000;
        this.maxPv = pv;
    }

    public <T extends AEnemy> void lostLife(T enemy) {
        this.pv -= enemy.getDamage();
    }

    @Override
    public void upgradeLvl2() {
        this.upgradeDamage(5);
        this.upgradeRange(10);
        this.targetNumber += 5;
        this.level++;
    }

    @Override
    public void upgradeLvl3() {
        this.upgradeDamage(5);
        this.upgradeRange(10);
        this.targetNumber = targetNumber + 5;
        this.level++;
    }
}
