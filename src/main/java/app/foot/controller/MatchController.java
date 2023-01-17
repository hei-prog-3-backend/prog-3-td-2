package app.foot.controller;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.service.MatchService;
import app.foot.service.PlayerScoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MatchController {
    private final MatchService service;

    private final PlayerScoreService playerScoreService;

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return service.getMatches();
    }

    @GetMapping("/matches/{matchId}")
    public List<Match> getMatch(@PathVariable Integer matchId) {
        return service.getMatch(matchId);
    }

    @PostMapping("/matches/{matchId}/goals")
    public List<Match> createGoals(@PathVariable Integer matchId, @RequestBody List<PlayerScorer> toCreate) {
        playerScoreService.createPlayerScorer(matchId,toCreate);
        return service.getMatch(matchId);
    }


}
