package app.foot.repository.mapper;

import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isGuardian(entity.isGuardian())
                .build();
    }

    public PlayerScorer toDomain(PlayerScoreEntity entity) {
        return PlayerScorer.builder()
                .player(toDomain(entity.getPlayer()))
                .minute(entity.getMinute())
                .isOwnGoal(entity.isOwnGoal())
                .build();
    }


}
