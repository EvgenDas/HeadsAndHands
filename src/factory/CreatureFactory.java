package factory;

import creatures.Creature;
import creatures.CreatureType;
import creatures.Monster;
import creatures.Player;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class is a factory for creating creatures
 */

public class CreatureFactory {

  public static final Logger logger = Logger.getLogger(CreatureFactory.class.getName());

  /**
   * Getting the necessary creatures and pre-checking the parameters.
   *
   * @param type      Creature type
   * @param attack    attack parameters
   * @param defense   defense parameters
   * @param maxHealth maxHealth parameters
   * @param damageMin damageMin parameters
   * @param damageMax damageMax parameters
   * @return Creature or null if wrong parameters
   */
  public Creature getCreature(CreatureType type,
      int attack, int defense, int maxHealth, int damageMin, int damageMax) {

    if (!checkParameters(attack, defense, maxHealth,
        damageMin, damageMax)) {
      logger.log(Level.INFO, "Введены некорректные параметры,"
          + " объект не может быть создан. "
          + "Возвращён null");
      return null;
    }

    return switch (type) {
      case PLAYER -> new Player(attack, defense, maxHealth, damageMin, damageMax);
      case MONSTER -> new Monster(attack, defense, maxHealth, damageMin, damageMax);
    };
  }

  private boolean checkParameters(int attack, int defense,
      int maxHealth, int damageMin, int damageMax) {

    boolean check = true;

    if (attack < 1 || attack > 30) {
      logger.log(Level.INFO, "Неправильное значение для параметра Атака, "
          + "оно должно быть от 1 до 30");
      check = false;
    }

    if (defense < 1 || defense > 30) {
      logger.log(Level.INFO, "Неправильное значение для параметра Защита, "
          + "оно должно быть от 1 до 30");
      check = false;
    }

    if (maxHealth < 0) {
      logger.log(Level.INFO, "Неправильное значение для параметра Здоровье, "
          + "оно должно быть больше 0 либо равно 0");
      check = false;
    }

    if (damageMin > damageMax) {
      logger.log(Level.INFO, "Неправильные значения для параметра Урон, "
          + "диапазон должен идти от меньшего к большему");
      check = false;
    }
    return check;
  }
}
