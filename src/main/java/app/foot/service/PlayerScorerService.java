package app.foot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import app.foot.model.PlayerScorer;
import app.foot.repository.PlayerScoreRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerScorerService {
    private final PlayerService playerService;
    private final MatchService matchService;
    private final PlayerScoreRepository repository;
    public List<PlayerScoreEntity> createMatches(int matcheId,List<PlayerScorer> addPlayerScorers){
        List<PlayerScoreEntity> domaine = new ArrayList<>();
        for(PlayerScorer toDomaine : addPlayerScorers){
            int idPlayer = toDomaine.getPlayer().getId();
            PlayerEntity playerById = playerService.getPlayerById(idPlayer);
            MatchEntity matchById = matchService.getMatchesByid(matcheId);
            domaine.add(PlayerScoreEntity.builder()
                            .player(playerById)
                            .match(matchById)
                            .minute(addPlayerScorers.get(0).getMinute())
                            .ownGoal(addPlayerScorers.get(0).getIsOwnGoal())
                    .build());
        }
        return repository.saveAll(domaine);
    }
}
