package Towers;

public interface ITower {
    void upgradeDamage(float damage);

    void upgradeRange(int range);

    int getLevel();

    int getTargetNumber();

    void setDamage(float damage);

    float getDamage();

    void setRange(int range);

    int getRange();
}
