package app.foot.repository.mapper;

import app.foot.model.Goal;
import app.foot.model.Match;
import app.foot.repository.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GoalMapper {
    private final PlayerMapper playerMapper;
    private final MatchMapper matchMapper;

    public Goal toDomain(GoalEntity goalEntity) {
        Integer matchId = getMatchId(goalEntity, goalEntity.getId());
        Boolean player = isNotGuardian(goalEntity, goalEntity.getPlayerType());
        return Goal.builder()
                .id(goalEntity.getId())
                .match(Match.builder()
                        .id(matchId)
                        .build())
                .player(player)
                .build();
    }

    private Integer getMatchId(GoalEntity goalEntity, Integer id) {
        return goalEntity.getId();
    }


    private static int theMatchId(MatchEntity match, Integer id) {
        return match.getId();
    }

    private static boolean isNotGuardian(PlayerEntity player, Boolean type) {
        return !player.isGuardian();
    }
}
