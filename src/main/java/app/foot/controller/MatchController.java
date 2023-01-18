package app.foot.controller;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.entity.Validator.CreatePlayerScoreMinutesValidator;
import app.foot.repository.entity.Validator.CreatePlayerScoreValidator;
import app.foot.repository.mapper.PlayerScoreMapper;
import app.foot.service.MatchService;
import app.foot.service.PlayerScoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MatchController {
    private final MatchService service;

    private final CreatePlayerScoreMinutesValidator minutesValidator;

    private final CreatePlayerScoreValidator validator;

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return service.getMatches();
    }

    @PostMapping("/matches/{matchesId}/goal")
    public List<Match> createMatchesIdGoal( @PathVariable Integer matchesId,
                                            @RequestBody PlayerScorer toCreate) {

        validator.accept(toCreate.getPlayer());
        minutesValidator.accept(toCreate);

        return service.getMatchesCreate(toCreate , matchesId);
    }
}
