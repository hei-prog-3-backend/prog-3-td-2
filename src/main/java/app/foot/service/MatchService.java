package app.foot.service;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.model.validator.PlayerScorerValidator;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerScorerRepository;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.mapper.MatchMapper;
import app.foot.repository.mapper.PlayerMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatchService {
    private final MatchRepository repository;
    private final PlayerScorerRepository scorerRepository;
    private final MatchMapper mapper;
    private final PlayerMapper playerMapper;
    private final PlayerScorerValidator playerScorerValidator;

    public List<Match> getMatches() {
        return repository.findAll().stream()
            .map(mapper::toDomain)
            .toList();
    }

    public List<Match> addGoals(int matchId, PlayerScorer goal) {
        playerScorerValidator.accept(goal);
        PlayerScoreEntity score = scorerRepository.save(playerMapper.toEntity(matchId, goal));
        return List.of(mapper.toDomain(score.getMatch()));
    }
}
