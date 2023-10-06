package creatures;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Monster extends Creature {

  public static final Logger logger = Logger.getLogger(Monster.class.getName());

  public Monster(int attack, int defense, int maxHealth, int damageMin, int damageMax) {
    super(attack, defense, maxHealth, damageMin, damageMax);
    logger.log(Level.INFO, "Создан объект класса Monster");
  }

  @Override
  public void hitLog(Creature creature) {
    logger.log(Level.INFO, "Монстр атакует");
    if (!hit(creature)) {
      logger.log(Level.INFO, "Монстр атаковал неудачно");
    }
  }
}
