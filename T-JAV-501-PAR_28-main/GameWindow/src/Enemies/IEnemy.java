package Enemies;

public interface IEnemy {

    float getPv();

    float getSpeed();

    void upgradePv(int newPv);

    void setSpeed(int damage);

    int getCoin();

    void animatedCharacter();

    void move();

   // void takeDamage(float damage);

}