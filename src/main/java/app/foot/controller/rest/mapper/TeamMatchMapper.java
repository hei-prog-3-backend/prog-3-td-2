package app.foot.controller.rest.mapper;

import app.foot.controller.rest.TeamMatch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeamMatchMapper {
  private final TeamRestMapper mapper;
  private final PlayerScorerRestMapper playerScorerRestMapper;

  public TeamMatch toRest(app.foot.model.TeamMatch domain) {
    return TeamMatch.builder()
        .team(mapper.toRest(domain.getTeam()))
        .score(domain.getScore())
        .scorers(domain.getScorers().stream()
            .map(playerScorerRestMapper::toRest)
            .toList())
        .build();
  }
}
