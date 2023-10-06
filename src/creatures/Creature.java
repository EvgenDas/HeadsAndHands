package creature;

import java.util.concurrent.ThreadLocalRandom;
import PlayingDice;


public class Creature {

  // 1-30
  protected int attack;
  // 1-30
  protected int defense;
  // 1-N
  protected int maxHealth;
  // 1-maxHealth
  protected int currentHealth;

  //M-N
  protected int damageMin;
  protected int damageMax;

  private PlayingDice playingDice;

  public int getAttack() {
    return attack;
  }

  public int getDefense() {
    return defense;
  }

  public void setCurrentHealth(int currentHealth) {
    this.currentHealth = currentHealth;
  }

  public int getCurrentHealth() {
    return currentHealth;
  }

  public boolean isAlive() {
    return currentHealth > 0;
  }

  public void hit(Creature creature) {
    int attackModifier = attack - creature.getDefense() + 1;
    if (attackModifier < 1) {
      attackModifier = 1;
    }

    if (playingDice.impactSuccess(attackModifier)) {
      int loss = ThreadLocalRandom
          .current()
          .nextInt(damageMin, damageMax + 1);
      creature.setCurrentHealth(creature.getCurrentHealth() - loss);
    }
  }
}
