package creature;

public class Player extends Creature {

  private int countHealing = 4;


  public void healing() {
    if (countHealing > 0) {
      if (currentHealth + maxHealth * 0.3 <= maxHealth) {
        currentHealth += maxHealth;
      } else {
        currentHealth = maxHealth;
      }
    }
    countHealing--;
  }

}
