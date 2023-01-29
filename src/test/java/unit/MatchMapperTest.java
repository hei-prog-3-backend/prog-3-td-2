package unit;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.model.TeamMatch;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.mapper.MatchMapper;
import app.foot.repository.mapper.PlayerMapper;
import app.foot.repository.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utils.TestUtils.*;

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

        Match actual = subject.toDomain(MatchEntity.builder()
                .teamA(teamBarea())
                .teamB(teamGhana())
                .scorers(List.of(scorerRakoto(playerEntityRakoto(teamBarea()))))
                .build());

        assertEquals(
                Match.builder()
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
                        .build(), actual);
    }
}
