package app.foot.model.validator;

import app.foot.model.PlayerScorer;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

@Component
public class PlayerScorerValidator implements Consumer<PlayerScorer> {
  @Override
  public void accept(PlayerScorer playerScorer) {
    StringBuilder errorMessage = new StringBuilder();
    if (playerScorer.getPlayer().getIsGuardian()) {
      errorMessage.append("A goalkeeper cannot score. ");
    }
    if (playerScorer.getMinute() < 0 || playerScorer.getMinute() > 90) {
      errorMessage.append("Goal time should be between 0 and 90.");
    }
    if (!errorMessage.isEmpty()) {
      throw new RuntimeException(errorMessage.toString());
    }
  }
}
