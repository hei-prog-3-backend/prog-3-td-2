package app.foot.controller;

import app.foot.controller.response.CreateScorersResponse;
import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.mapper.MatchMapper;
import app.foot.repository.mapper.PlayerMapper;
import app.foot.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MatchController {
    private final MatchService service;
    private final MatchMapper matchMapper;
    private final PlayerMapper playerMapper;

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return service.getMatches();
    }

    @PostMapping("/matches/{matchId}/goals")
    public List<Match> postGoals(@PathVariable Integer matchId, @RequestBody List<PlayerScoreEntity> scorers){
        return service.postGoalsToMatch(scorers,matchId).stream()
                .map(matchMapper::toDomain)
                .toList();
    }
}
