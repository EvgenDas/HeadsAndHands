import creatures.Creature;
import creatures.CreatureType;
import factory.CreatureFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Example {

  private Creature player;
  private List<Creature> monsters;

  private final CreatureFactory factory = new CreatureFactory();

  public static final Logger logger = Logger.getLogger(Example.class.getName());


  public static void main(String[] args) {
    Example example = new Example();
    example.playerBattleAgainstFourWavesOfMonsters();
  }

  public void playerBattleAgainstFourWavesOfMonsters() {
    createPlayerAndMonstersForFourMonstersWaves();
    int i = 1;
    logger.log(Level.INFO, "Битва началась");
    while (player.isAlive() && i <= 4) {
      fight(i);
      i++;
    }
  }

  private void fight(int i) {

    List<Creature> monstersInFight = getMonstersForFight(i);

    while (monstersAreAlive(monstersInFight) && player.isAlive()) {
      if (player.isAlive() && player.getCurrentHealth() < 0.3 * player.getMaxHealth()) {
        player.healing();
      }

      playerHit(monstersInFight);

      if (monstersAreAlive(monstersInFight)) {
        monstersHits(monstersInFight);
      } else {
        logger.log(Level.INFO, "Монстры {0} волны убиты", i);
        logger.log(Level.INFO, "Игрок победил в {0} волне", i);
      }
    }
    if (!player.isAlive()) {
      logger.log(Level.INFO, "Игрок мёртв");
      logger.log(Level.INFO, "Игрок проиграл в {0} волне", i);
    }

  }

  private List<Creature> getMonstersForFight(int i) {
    List<Creature> monstersForFight;
    switch (i) {
      case 1 -> monstersForFight = monsters.subList(0, 1);
      case 2 -> monstersForFight = monsters.subList(1, 3);
      case 3 -> monstersForFight = monsters.subList(3, 6);
      case 4 -> monstersForFight = monsters.subList(6, 10);
      default -> monstersForFight = monsters.subList(0, 0);
    }
    return monstersForFight;
  }

  private void monstersHits(List<Creature> monstersInFight) {
    for (Creature creature : monstersInFight) {
      if (player.isAlive()) {
        creature.hitLog(player);
      }

    }
  }

  private void playerHit(List<Creature> monstersInFight) {
    for (Creature creature : monstersInFight) {
      player.hitLog(creature);
    }
  }

  private boolean monstersAreAlive(List<Creature> monstersInFight) {
    for (Creature creature : monstersInFight) {
      if (creature.isAlive()) {
        return true;
      }
    }
    return false;
  }


  private void createPlayerAndMonstersForFourMonstersWaves() {
    createRandomPlayer();
    createRandomMonsters();
  }

  private void createRandomPlayer() {
    int attack = ThreadLocalRandom.current().nextInt(1, 30 + 1);
    int defense = ThreadLocalRandom.current().nextInt(1, 30 + 1);
    int maxHealth = 100;
    int damageMin = 10;
    int damageMax = 50;

    player = factory.getCreature(CreatureType.PLAYER, attack, defense,
        maxHealth, damageMin, damageMax);
  }

  private void createRandomMonsters() {
    int attack = ThreadLocalRandom.current().nextInt(1, 30 + 1);
    int defense = ThreadLocalRandom.current().nextInt(1, 30 + 1);
    int maxHealth = 20;
    int damageMin = 10;
    int damageMax = 20;

    monsters = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      monsters.add(factory.getCreature(CreatureType.MONSTER, attack, defense,
          maxHealth, damageMin, damageMax));

      attack = ThreadLocalRandom.current().nextInt(1, 30 + 1);
      defense = ThreadLocalRandom.current().nextInt(1, 30 + 1);
      maxHealth += 20;
      damageMin += 5;
      damageMax += 5;
    }
  }


}
