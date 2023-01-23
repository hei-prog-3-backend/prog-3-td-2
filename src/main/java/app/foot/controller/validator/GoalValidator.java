package app.foot.controller.validator;

import app.foot.controller.rest.PlayerScorer;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

@Component
public class GoalValidator implements Consumer<PlayerScorer> {

  @Override
  public void accept(PlayerScorer playerScorer) {
    StringBuilder exceptionBuilder = new StringBuilder();
    if (playerScorer.getPlayer().getIsGuardian()) {
      exceptionBuilder.append("Player#")
          .append(playerScorer.getPlayer().getId())
          .append(" is a guardian ").append("so they cannot score.");
    }
    if (playerScorer.getScoreTime() == null) {
      exceptionBuilder.append("Score minute is mandatory.");
    }
    if (playerScorer.getScoreTime() < 0) {
      exceptionBuilder.append("Player#")
          .append(playerScorer.getPlayer().getId())
          .append(" cannot score before before minute 0.");
    }
    if (playerScorer.getScoreTime() > 90) {
      exceptionBuilder.append("Player#")
          .append(playerScorer.getPlayer().getName())
          .append(" cannot score before after minute 90.");
    }
    if (!exceptionBuilder.isEmpty()) {
      throw new RuntimeException(exceptionBuilder.toString());
    }
  }
}
