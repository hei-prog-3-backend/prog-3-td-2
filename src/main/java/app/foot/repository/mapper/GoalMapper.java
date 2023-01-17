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
        return Goal.builder()
                .id(goalEntity.getId())
                .match(Match.builder()
                        .id(matchMapper.toDomain(goalEntity.getId()))
                .build();

    }

    private List<Goal> getMatchId(GoalEntity goalEntity, MatchEntity matchId) {
        return goalEntity.getMatch().stream()
                .filter(match -> match.getId() == matchId.getId())
                .map(matchMapper::toDomain)
                .toList();
    }


    private static int theMatchId(MatchEntity match) {
        return match.getId();
    }

    private static boolean isNotGardian(PlayerEntity player) {
        return !player.isGuardian();
    }
}
