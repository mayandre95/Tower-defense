package Enemies;

import Window.PlayingWindow;

public class SkullKing extends AEnemy {

    public SkullKing(PlayingWindow playingWindow) {
        super(0.9f, 150, 15, 0, 100, playingWindow);
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