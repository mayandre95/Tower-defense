package Towers;

import Window.PlayingWindow;

public class Mage extends ATower {

    public Mage(float xCoord, float yCoord, PlayingWindow playingWindow) {
        super(50, 20, 1, 5, 0, playingWindow);
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    @Override
    public void upgradeLvl2() {
        this.upgradeDamage(5);
        this.upgradeRange(10);
        this.targetNumber = targetNumber + 5;
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
