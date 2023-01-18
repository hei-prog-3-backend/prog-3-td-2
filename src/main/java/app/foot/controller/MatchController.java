package app.foot.controller;

import app.foot.exception.BadException;
import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.service.MatchService;
import app.foot.service.PlayerScorerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MatchController {
    private final MatchService service;
    private final PlayerScorerService playerScorerService;

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return service.getMatches();
    }

    @PostMapping("/matches/{matchId}/goal")
    public List<PlayerScoreEntity> creatGoals(@PathVariable int matchId, @RequestBody List<PlayerScorer> playerScorer){
        for (PlayerScorer playerScorer1 : playerScorer){
            if (playerScorer1.getPlayer().getIsGuardian()){
                throw new BadException("le gardien de but ne dois pas marque le but");
            }
            if(playerScorer1.getMinute() < 0 && playerScorer1.getMinute() < 90){
                throw new BadException("le but marque dois entre [1 minute et 90 minutes]");
            }

        }
        return playerScorerService.createMatches(matchId,playerScorer);
    }
}
