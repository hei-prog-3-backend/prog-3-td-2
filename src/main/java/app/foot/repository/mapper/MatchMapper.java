package app.foot.repository.mapper;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.model.TeamMatch;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.entity.TeamEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MatchMapper {
    private final TeamMapper teamMapper;
    private final PlayerMapper playerMapper;

    public Match toDomain(MatchEntity matchEntity) {
        List<PlayerScorer> teamBScorers = getTeamScorers(matchEntity, matchEntity.getTeamB());
        List<PlayerScorer> teamAScorers = getTeamScorers(matchEntity, matchEntity.getTeamA());
        return Match.builder()
                .id(matchEntity.getId())
                .datetime(matchEntity.getDatetime())
                .stadium(matchEntity.getStadium())
                .teamA(TeamMatch.builder()
                        .team(teamMapper.toDomain(matchEntity.getTeamA()))
                        .scorers(teamAScorers)
                        .score(teamAScorers.size())
                        .build())
                .teamB(TeamMatch.builder()
                        .team(teamMapper.toDomain(matchEntity.getTeamB()))
                        .scorers(teamBScorers)
                        .score(teamBScorers.size())
                        .build())
                .build();
    }

    private List<PlayerScorer> getTeamScorers(MatchEntity matchEntity, TeamEntity team) {
        return matchEntity.getScorers().stream()
                .filter(scorer -> isGoalAgainstOpponent(team, scorer) || isOpponentOwnGoal(team, scorer))
                .map(playerMapper::toDomain)
                .toList();
    }

    private static boolean isOpponentOwnGoal(TeamEntity team, PlayerScoreEntity scorer) {
        return !scorer.getPlayer().getTeam().equals(team) && scorer.isOwnGoal();
    }

    private static boolean isGoalAgainstOpponent(TeamEntity team, PlayerScoreEntity scorer) {
        return scorer.getPlayer().getTeam().equals(team) && !scorer.isOwnGoal();
    }
}
