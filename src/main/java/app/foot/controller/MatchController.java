package app.foot.controller;

import app.foot.controller.rest.Match;
import app.foot.controller.rest.PlayerScorer;
import app.foot.controller.rest.mapper.MatchRestMapper;
import app.foot.controller.rest.mapper.PlayerScorerRestMapper;
import app.foot.controller.validator.GoalValidator;
import app.foot.service.MatchService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MatchController {
  private final MatchService service;
  private final GoalValidator validator;
  private final MatchRestMapper mapper;
  private final PlayerScorerRestMapper scorerMapper;

  @GetMapping("/matches")
  public List<Match> getMatches() {
    return service.getMatches().stream()
        .map(mapper::toRest)
        .toList();
  }

  @PostMapping("/matches/{matchId}/goals")
  public Match addGoals(@PathVariable int matchId, @RequestBody List<PlayerScorer> scorers) {
    scorers.forEach(validator);
    List<app.foot.model.PlayerScorer> scorerList = scorers.stream()
        .map(scorerMapper::toDomain)
        .toList();
    return mapper.toRest(service.addGoals(matchId, scorerList));
  }
}
