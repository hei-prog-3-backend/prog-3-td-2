package app.foot.controller.rest.mapper;

import app.foot.controller.rest.Match;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MatchRestMapper {
  private final TeamMatchMapper teamMatchMapper;

  public Match toRest(app.foot.model.Match match) {
    return Match.builder()
        .id(match.getId())
        .datetime(match.getDatetime())
        .teamA(teamMatchMapper.toRest(match.getTeamA()))
        .teamB(teamMatchMapper.toRest(match.getTeamB()))
        .stadium(match.getStadium())
        .build();
  }
}
