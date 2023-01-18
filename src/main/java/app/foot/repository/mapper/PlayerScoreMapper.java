package app.foot.repository.mapper;

import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerScoreMapper {

    public PlayerScorer toRest(PlayerScoreEntity playerScorerEntity){

        return PlayerScorer.builder()
                .player(Player.builder()
                        .id(playerScorerEntity.getId())
                        .name(playerScorerEntity.getPlayer().getName())
                        .isGuardian(playerScorerEntity.getPlayer().isGuardian())
                        .build())
                .isOwnGoal(playerScorerEntity.isOwnGoal())
                .minute(playerScorerEntity.getMinute())
                .build();
    }

    public PlayerScoreEntity toDomain(PlayerScorer playerScorer){

        return PlayerScoreEntity.builder()
                .player(PlayerEntity.builder().id(playerScorer.getPlayer().getId())
                        .name(playerScorer.getPlayer().getName())
                        .guardian(playerScorer.getPlayer().getIsGuardian())
                        .build())
                .ownGoal(playerScorer.getIsOwnGoal())
                .minute(playerScorer.getMinute())
                .build();

    }

}
