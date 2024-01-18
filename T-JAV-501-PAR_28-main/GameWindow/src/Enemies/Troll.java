package Enemies;

import Window.PlayingWindow;

public class Troll extends AEnemy {

    public Troll(PlayingWindow playingWindow) {
        super(0.4f, 200, 30, 4, 50, playingWindow);
        listEnemies.add(this);

    }

    @Override
    public void upgradeLvl1() {
        this.upgradeDamage(10);
        this.upgradePv(50);
    }

    @Override
    public void upgradeLvl2() {
        this.upgradeDamage(15);
        this.upgradePv(70);
    }

    @Override
    public void animatedCharacter() {

    }

}
