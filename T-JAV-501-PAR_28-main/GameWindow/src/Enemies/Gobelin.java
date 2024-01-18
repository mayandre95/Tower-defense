package Enemies;

import Window.PlayingWindow;

public class Gobelin extends AEnemy {

    public Gobelin(PlayingWindow playingWindow) {
        super(0.6f, 50, 8, 2, 15, playingWindow);
        listEnemies.add(this);
    }

    @Override
    public void upgradeLvl1() {
        this.upgradeDamage(5);
        this.upgradePv(20);
    }

    @Override
    public void upgradeLvl2() {
        this.upgradeDamage(1);
        this.upgradePv(50);
    }

    @Override
    public void animatedCharacter() {

    }

}
