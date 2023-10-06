import java.util.concurrent.ThreadLocalRandom;

public class PlayingDice {

  public boolean impactSuccess(int n) {
    for (int i = 0; i < n; i++) {
      if (successfulDiceRoll()) {
        return true;
      }
    }
    return false;
  }

  private boolean successfulDiceRoll() {
    int rollNumber = ThreadLocalRandom.current().nextInt(1, 6 + 1);
    return rollNumber == 5 || rollNumber == 6;
  }
}
