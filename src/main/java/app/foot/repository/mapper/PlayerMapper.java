package app.foot.repository.mapper;

import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerMapper {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

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

    public PlayerScoreEntity toEntity(int matchId, PlayerScorer domain) {
        Optional<MatchEntity> match = matchRepository.findById(matchId);
        if (match.isPresent()) {
            Optional<PlayerEntity> player =
                playerRepository.findByIdAndNameAndGuardian(domain.getPlayer().getId(),
                    domain.getPlayer().getName(), domain.getPlayer().getIsGuardian());
            if (player.isPresent()) {
                return PlayerScoreEntity.builder()
                    .player(player.get())
                    .minute(domain.getMinute())
                    .ownGoal(domain.getIsOwnGoal())
                    .match(match.get())
                    .build();
            }
            throw new RuntimeException("Player not found");
        }
        throw new RuntimeException("Match not found");
    }
}
