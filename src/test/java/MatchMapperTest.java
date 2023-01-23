import app.foot.model.*;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.entity.TeamEntity;
import app.foot.repository.mapper.MatchMapper;
import app.foot.repository.mapper.PlayerMapper;
import app.foot.repository.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class MatchMapperTest {
    TeamMapper teamMapper = mock(TeamMapper.class);
    PlayerMapper playerMapper = mock(PlayerMapper.class);
    MatchMapper subject = new MatchMapper(teamMapper, playerMapper);

    @Test
    void to_domain_ok() {
        PlayerScorer scorer = rakotoModelScorer(
                playerModelRakoto(playerEntityRakoto(teamBarea())),
                scorerRakoto(playerEntityRakoto(teamBarea())));


        when(teamMapper.toDomain(teamBarea())).thenReturn(teamModelBarea(teamBarea()));
        when(teamMapper.toDomain(teamGhana())).thenReturn(teamModelGhana(teamGhana()));
        when(playerMapper.toDomain(scorerRakoto(playerEntityRakoto(teamBarea()))))
                .thenReturn(scorer);


        Match expected = Match.builder()
                .teamA(TeamMatch.builder()
                        .team(teamModelBarea(teamBarea()))
                        .score(1)
                        .scorers(List.of(scorer))
                        .build())
                .teamB(TeamMatch.builder()
                        .team(teamModelGhana(teamGhana()))
                        .score(0)
                        .scorers(List.of())
                        .build())
                .build();

        Match actual = subject.toDomain(MatchEntity.builder()
                .teamA(teamBarea())
                .teamB(teamGhana())
                .scorers(List.of(scorerRakoto(playerEntityRakoto(teamBarea()))))
                .build());

        assertEquals(expected, actual);
    }

    private static PlayerScorer rakotoModelScorer(Player playerModelRakoto, PlayerScoreEntity scorerRakoto) {
        return PlayerScorer.builder()
                .player(playerModelRakoto)
                .isOwnGoal(false)
                .minute(scorerRakoto.getMinute())
                .build();
    }

    private static Team teamModelGhana(TeamEntity teamEntityGhana) {
        return Team.builder()
                .id(teamEntityGhana.getId())
                .name(teamEntityGhana.getName())
                .build();
    }

    private static Team teamModelBarea(TeamEntity teamEntityBarea) {
        return Team.builder()
                .id(teamEntityBarea.getId())
                .name(teamEntityBarea.getName())
                .build();
    }

    private static PlayerScoreEntity scorerRakoto(PlayerEntity playerEntityRakoto) {
        return PlayerScoreEntity.builder()
                .id(1)
                .player(playerEntityRakoto)
                .minute(10)
                .build();
    }

    private static Player playerModelRakoto(PlayerEntity playerEntityRakoto) {
        return Player.builder()
                .id(playerEntityRakoto.getId())
                .name(playerEntityRakoto.getName())
                .isGuardian(false)
                .build();
    }

    private static PlayerEntity playerEntityRakoto(TeamEntity teamEntityBarea) {
        return PlayerEntity.builder()
                .id(1)
                .name("Rakoto")
                .guardian(false)
                .team(teamEntityBarea)
                .build();
    }

    private static TeamEntity teamGhana() {
        return TeamEntity.builder()
                .id(2)
                .name("Ghana")
                .build();
    }

    private static TeamEntity teamBarea() {
        return TeamEntity.builder()
                .id(1)
                .name("Barea")
                .build();
    }
}
