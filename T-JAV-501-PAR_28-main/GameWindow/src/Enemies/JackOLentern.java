package Enemies;

import Window.PlayingWindow;

public class JackOLentern extends AEnemy {

    public JackOLentern(PlayingWindow playingWindow) {
        super(0.5f, 150, 15, 1, 100, playingWindow);
        listEnemies.add(this);
    }

    @Override
    public void upgradeLvl1() {
        this.upgradeDamage(8);
        this.upgradePv(50);
    }

    @Override
    public void upgradeLvl2() {
        this.upgradeDamage(15);
        this.upgradePv(80);
    }

    @Override
    public void animatedCharacter() {

    }

}