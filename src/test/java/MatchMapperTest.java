import app.foot.model.Match;
import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.model.Team;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.mapper.MatchMapper;
import app.foot.repository.mapper.PlayerMapper;
import app.foot.repository.mapper.TeamMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchMapperTest {
    TeamMapper teamMapper = mock(TeamMapper.class);
    PlayerMapper playerMapper = mock(PlayerMapper.class);
    MatchMapper subject = new MatchMapper(teamMapper, playerMapper);

    @Test
    void to_domain_ok() {
        when(teamMapper.toDomain(any())).thenReturn(Team.builder().build());
        when(playerMapper.toDomain((PlayerScoreEntity) any())).thenReturn(PlayerScorer.builder().build());
        when(playerMapper.toDomain((PlayerEntity) any())).thenReturn(Player.builder().build());
        Match expected = Match.builder().build();

        Match actual = subject.toDomain(MatchEntity.builder()
                .scorers(List.of())
                .build());

        assertEquals(expected, actual);
    }

}
