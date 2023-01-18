package app.foot.service;

import app.foot.model.PlayerScorer;
import app.foot.repository.PlayerScoreRepository;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.mapper.PlayerScoreMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerScoreService {
    private final PlayerScoreRepository repository;

    public PlayerScoreEntity createScore(PlayerScoreEntity playerScorer, Integer id) {
        return repository.save(playerScorer);
    }
}
