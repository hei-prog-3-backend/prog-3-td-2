package app.foot.service;

import app.foot.model.Match;
import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerScoreRepository;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.mapper.MatchMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchService {
    private final MatchRepository repository;
    private final MatchMapper mapper;

    private final PlayerScoreService playerScoreService;

    public List<Match> getMatches() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    public List<Match> getMatchesCreate(PlayerScorer playerScorer, Integer id){
        PlayerScoreEntity playerscorer = playerScoreService.findById(playerScorer.getPlayer().getId());

            playerScoreService.createScore(playerscorer,id);

        return repository.findById(id).stream()
                .map(mapper :: toDomain )
                .toList();
    }
}
