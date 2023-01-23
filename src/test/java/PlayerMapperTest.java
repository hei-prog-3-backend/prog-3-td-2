import app.foot.model.Player;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerRepository;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.TeamEntity;
import app.foot.repository.mapper.PlayerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO-2: complete these tests
public class PlayerMapperTest {
PlayerRepository playerRepository = mock(PlayerRepository.class);
MatchRepository matchRepository=mock(MatchRepository.class);
    PlayerMapper playerMapper = mock(PlayerMapper.class);

    TeamEntity teamMada (){
        return TeamEntity.builder()
                .id(1)
                .name("Mada")
                .build();
    }

    PlayerEntity rakotoPlayer1(){
        return PlayerEntity.builder()
                .id(1)
                .name("rakoto")
                .team(teamMada())
                .guardian(false)
                .build();
    }

    public  static Player PlayerRakotoModel(PlayerEntity playerEntity){
        return Player.builder()
                .id(playerEntity.getId())
                .name(playerEntity.getName())
                .teamName(playerEntity.getTeam().getName())
                .isGuardian(playerEntity.isGuardian())
                .build();
    }
    @Test
    void player_to_domain_ok() {


        when(playerMapper.toDomain(rakotoPlayer1())).thenReturn(PlayerRakotoModel(rakotoPlayer1()));

        Player excepted = Player.builder()
                .id(rakotoPlayer1().getId())
                .name(rakotoPlayer1().getName())
                .isGuardian(false)
                .teamName(rakotoPlayer1().getTeam().getName())
                .build();
        Player actual = playerMapper.toDomain(rakotoPlayer1());

        assertEquals(excepted,actual);

    }

    @Test
    void player_scorer_to_domain_ok() {


    }

    @Test
    void player_scorer_to_entity_ok() {

    }
}
