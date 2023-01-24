import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.entity.TeamEntity;
import app.foot.repository.mapper.PlayerMapper;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO-2: complete these tests
public class PlayerMapperTest {
static PlayerRepository playerRepository = mock(PlayerRepository.class);
static MatchRepository matchRepository=mock(MatchRepository.class);
    PlayerMapper playerMapper = mock(PlayerMapper.class);

    PlayerMapper subject = new PlayerMapper(matchRepository, playerRepository);


    static TeamEntity teamMada(){
        return TeamEntity.builder()
                .id(1)
                .name("Mada")
                .build();
    }

    static PlayerEntity rakotoPlayer1(){
        return PlayerEntity.builder()
                .id(1)
                .name("rakoto")
                .team(teamMada())
                .guardian(false)
                .build();
    }

    static PlayerScoreEntity playerScoreEntityExcepted(){
        return PlayerScoreEntity.builder()
                .player(PlayerEntity.builder()
                        .id(1)
                        .build())
                .match(MatchEntity.builder()
                        .id(1)
                        .build())
                .minute(5)
                .ownGoal(true)
                .build();
    }

    static PlayerScorer playerScorer(){
        return PlayerScorer.builder()
                .player(PlayerRakotoModel(rakotoPlayer1()))
                .isOwnGoal(playerScoreEntityExcepted().isOwnGoal())
                .minute(playerScoreEntityExcepted().getMinute())
                .isOwnGoal(playerScoreEntityExcepted().isOwnGoal())
                .build();
    }

    public  static Player PlayerRakotoModel(PlayerEntity playerEntity) {
        return Player.builder()
                .id(playerEntity.getId())
                .name(playerEntity.getName())
                .teamName(playerEntity.getTeam().getName())
                .isGuardian(playerEntity.isGuardian())
                .build();

    }
    static TeamEntity team1(){
        return TeamEntity.builder()
                .id(1)
                .name("mada")
                .build();
    }
    TeamEntity team2(){
        return TeamEntity.builder()
                .id(2)
                .name("Ghana")
                .build();
    }

    MatchEntity match (){
        return MatchEntity.builder()
                .id(1)
                .teamA(team1())
                .teamB(team2())
                .build();
    }

    PlayerScoreEntity scorer1(){
        return PlayerScoreEntity.builder()
                .id(1)
                .player(rakotoPlayer1())
                .match(match())
                .ownGoal(false)
                .minute(15)
                .build();
    }

    public static PlayerScorer PlayerScorerModel(PlayerScoreEntity playerScore){
        return PlayerScorer.builder()
                .player(PlayerRakotoModel(playerScore.getPlayer()))
                .minute(playerScore.getMinute())
                .isOwnGoal(playerScore.isOwnGoal())
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
        when(playerMapper.toDomain(scorer1())).thenReturn(PlayerScorerModel(scorer1()));
        PlayerScorer excepted = PlayerScorer.builder()
                .player(Player.builder()
                        .teamName(scorer1().getPlayer().getTeam().getName())
                        .isGuardian(scorer1().getPlayer().isGuardian())
                        .id(scorer1().getPlayer().getId())
                        .name(scorer1().getPlayer().getName())
                        .build())
                .isOwnGoal(scorer1().isOwnGoal())
                .minute(scorer1().getMinute())
                .build();

        PlayerScorer actual = playerMapper.toDomain(scorer1());

        assertEquals(excepted,actual);

    }
    private void setUpPlayerRepository(PlayerRepository repository) {
        when(repository.findById(any())).thenAnswer(i -> Optional.of(PlayerEntity.builder()
                .id(i.getArgument(0))
                .build()));
    }

    private void setUpMatchRepository(MatchRepository repository) {
        when(repository.findById(any())).thenAnswer(i -> Optional.of(MatchEntity.builder()
                .id(i.getArgument(0))
                .build())
        );
    }

    @Test
    void player_scorer_to_entity_ok() {
        setUpPlayerRepository(playerRepository);
        setUpMatchRepository(matchRepository);

       PlayerScoreEntity actual = subject.toEntity(1,playerScorer());

       assertEquals(playerScoreEntityExcepted(),actual);
    }
}
