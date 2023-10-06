package creatures;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import support.PlayingDice;

/**
 * An abstract class of a creature. It describes the fields of the main methods of each of the
 * species of creatures.
 */

public abstract class Creature {

  protected int attack;
  protected int defense;
  protected int maxHealth;
  protected int currentHealth;
  protected int damageMin;
  protected int damageMax;

  private final PlayingDice playingDice = new PlayingDice();

  public static final Logger logger = Logger.getLogger(Creature.class.getName());

  protected Creature(int attack, int defense, int maxHealth, int damageMin, int damageMax) {
    this.attack = attack;
    this.defense = defense;
    this.maxHealth = maxHealth;
    this.damageMin = damageMin;
    this.damageMax = damageMax;
    currentHealth = maxHealth;
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

  public int getMaxHealth() {
    return maxHealth;
  }

  public boolean isAlive() {
    return currentHealth > 0;
  }

  /**
   * Method that describes the impact of a given creature from a parameter.
   *
   * @param creature creature that is being attacked
   * @return success or failure of the attack
   */
  public boolean hit(Creature creature) {
    int attackModifier = attack - creature.getDefense() + 1;
    if (attackModifier < 1) {
      attackModifier = 1;
    }
    boolean luck = playingDice.impactSuccess(attackModifier);
    if (luck) {
      int loss = ThreadLocalRandom
          .current()
          .nextInt(damageMin, damageMax + 1);
      creature.setCurrentHealth(creature.getCurrentHealth() - loss);
      logger.log(Level.INFO, "Нанесён урон размером {0}", loss);
    }
    return luck;
  }

  /**
   * A method that performs an attack and logs its result.
   *
   * @param creature creature that is being attacked
   */

  public abstract void hitLog(Creature creature);

  /**
   * The method of healing the creature.
   * <br>
   * Override only the player.
   */

  public void healing() {
  }
}
