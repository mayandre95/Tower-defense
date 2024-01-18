package Towers;

import Window.PlayingWindow;

public class Knight extends ATower {

    public Knight(float xCoord, float yCoord, PlayingWindow playingWindow) {
        super(5, 20, 1, 5, 3, playingWindow);
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
        this.upgradeDamage(10);
        this.upgradeRange(20);
        this.targetNumber = targetNumber + 5;
        this.level++;
    }

}
