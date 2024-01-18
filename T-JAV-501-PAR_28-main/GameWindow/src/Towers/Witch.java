package Towers;

import Window.PlayingWindow;

public class Witch extends ATower {
    private int slowerDown;

    public Witch(float xCoord, float yCoord, PlayingWindow playingWindow) {
        super(20, 20, 1, 4, 1, playingWindow);
        this.slowerDown = 10;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    @Override
    public void upgradeLvl2() {
        this.upgradeDamage(15);
        this.upgradeRange(10);
        this.targetNumber = targetNumber + 5;
        this.level++;
        this.slowerDown = 20;
    }

    public void upgradeLvl3() {
        this.upgradeDamage(20);
        this.upgradeRange(15);
        this.targetNumber = targetNumber + 5;
        this.level++;
        this.slowerDown = 30;

    }

    public int getSlowerDown() {
        return slowerDown;
    }
}
