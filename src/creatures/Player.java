package creatures;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends Creature {

  public static final Logger logger = Logger.getLogger(Player.class.getName());

  private int countHealing = 4;

  public Player(int attack, int defense, int maxHealth, int damageMin, int damageMax) {
    super(attack, defense, maxHealth, damageMin, damageMax);
    logger.log(Level.INFO, "Создан объект класса Player");
  }

  /**
   * Player's healing method.
   * <br>
   * He can heal himself up to 4 times for 30% of maximum health.
   */
  @Override
  public void healing() {
    if (countHealing > 0) {
      if (currentHealth + maxHealth * 0.3 <= maxHealth) {
        currentHealth += (int) (maxHealth * 0.3);
      } else {
        currentHealth = maxHealth;
      }
      logger.log(Level.INFO, "Игрок исцелён до {0}", currentHealth);
      countHealing--;
    } else {
      logger.log(Level.INFO, "Возможности исцеления истрачены");
    }
  }

  @Override
  public void hitLog(Creature creature) {
    logger.log(Level.INFO, "Игрок атакует");
    if (!hit(creature)) {
      logger.log(Level.INFO, "Игрок атаковал неудачно");
    }
  }
}
