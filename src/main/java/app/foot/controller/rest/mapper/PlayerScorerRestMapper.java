package app.foot.controller.rest.mapper;

import app.foot.controller.rest.PlayerScorer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerScorerRestMapper {
  private final PlayerRestMapper playerRestMapper;

  public PlayerScorer toRest(app.foot.model.PlayerScorer domain) {
    return PlayerScorer.builder()
        .player(playerRestMapper.toRest(domain.getPlayer()))
        .isOG(domain.getIsOwnGoal())
        .scoreTime(domain.getMinute())
        .build();
  }
  public app.foot.model.PlayerScorer toDomain(PlayerScorer rest){
    return app.foot.model.PlayerScorer.builder()
        .isOwnGoal(rest.getIsOG())
        .player(playerRestMapper.toDomain(rest.getPlayer()))
        .minute(rest.getScoreTime())
        .build();
  }
}
