package utils;

import app.foot.controller.rest.Match;
import app.foot.controller.rest.Player;
import app.foot.controller.rest.PlayerScorer;
import app.foot.controller.rest.TeamMatch;
import app.foot.model.Team;
import app.foot.repository.TeamRepository;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.entity.TeamEntity;
import org.junit.jupiter.api.function.Executable;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class TestUtils {
    static TeamRepository teamRepositoryMock = mock(TeamRepository.class);

    public static PlayerScorer scorer1() {
        return PlayerScorer.builder()
                .player(player1())
                .isOG(false)
                .scoreTime(10)
                .build();
    }

    public static PlayerScorer scorer(){
        return PlayerScorer.builder()
                .player(scorerPlayer())
                .scoreTime(10)
                .isOG(false)
                .build();
    }

    public static Player scorerPlayer(){
        return Player.builder()
                .id(1)
                .name("J1")
                .isGuardian(false)
                .teamName("barea")
                .build();
    }

    public static PlayerScorer nullScoreTimeScorer() {
        return scorer1().toBuilder()
                .scoreTime(null)
                .build();
    }

    public static Player player1() {
        return Player.builder()
                .id(1)
                .name("Rakoto")
                .isGuardian(false)
                .build();
    }
    public static Player playertoScore1() {
        return Player.builder()
                .id(1)
                .name("J1")
                .teamName("E1")
                .isGuardian(false)
                .build();
    }

    public static  PlayerScorer playerScorer(){
        return  PlayerScorer.builder()
                .player(playertoScore1())
                .scoreTime(10)
                .isOG(false)
                .build();
    }
    public static app.foot.model.PlayerScorer rakotoModelScorer(app.foot.model.Player playerModelRakoto, PlayerScoreEntity scorerRakoto) {
        return app.foot.model.PlayerScorer.builder()
                .player(playerModelRakoto)
                .isOwnGoal(false)
                .minute(scorerRakoto.getMinute())
                .build();
    }

    public static Team teamModelGhana(TeamEntity teamEntityGhana) {
        return Team.builder()
                .id(teamEntityGhana.getId())
                .name(teamEntityGhana.getName())
                .build();
    }

    public static Team teamModelBarea(TeamEntity teamEntityBarea) {
        return Team.builder()
                .id(teamEntityBarea.getId())
                .name(teamEntityBarea.getName())
                .build();
    }

    public static PlayerScoreEntity scorerRakoto(PlayerEntity playerEntityRakoto) {
        return PlayerScoreEntity.builder()
                .id(1)
                .player(playerEntityRakoto)
                .minute(10)
                .build();
    }

    public static app.foot.model.Player playerModelRakoto(PlayerEntity playerEntityRakoto) {
        return app.foot.model.Player.builder()
                .id(playerEntityRakoto.getId())
                .name(playerEntityRakoto.getName())
                .isGuardian(false)
                .teamName(teamBarea().getName())
                .build();
    }

    public static PlayerEntity playerEntityRakoto(TeamEntity teamEntityBarea) {
        return PlayerEntity.builder()
                .id(1)
                .name("Rakoto")
                .guardian(false)
                .team(teamEntityBarea)
                .build();
    }

    public static TeamEntity teamGhana() {
        return TeamEntity.builder()
                .id(2)
                .name("Ghana")
                .build();
    }

    public static TeamEntity teamBarea() {
        return TeamEntity.builder()
                .id(1)
                .name("Barea")
                .build();
    }

    public static void assertThrowsExceptionMessage(String message, Class exceptionClass, Executable executable) {
        Throwable exception = assertThrows(exceptionClass, executable);
        assertEquals(message, exception.getMessage());
    }

    public static  PlayerEntity  playerEntityModel(app.foot.model.Player player){
        return  PlayerEntity.builder()
                .id(player.getId())
                .name(player.getName())
                .guardian(player.getIsGuardian())
                .team(teamBarea())
                .build();
    }
    public static app.foot.model.Player player(){
        return app.foot.model.Player.builder()
                .id(1)
                .name("J1")
                .isGuardian(false)
                .teamName(teamBarea().getName())
                .build();
    }

    public static app.foot.model.Player playerWithWrongTeam(){
        return app.foot.model.Player.builder()
                .id(2)
                .name("J2")
                .isGuardian(false)
                .teamName("W1")
                .build();
    }


    public static Match expectedMatch3() {
        return Match.builder()
                .id(3)
                .teamA(teamMatchA())
                .teamB(teamMatchB())
                .stadium("S3")
                .datetime(Instant.parse("2023-01-01T14:00:00Z"))
                .build();
    }

    public static TeamMatch teamMatchB() {
        return TeamMatch.builder()
                .team(team3())
                .score(0)
                .scorers(List.of())
                .build();
    }

    public static TeamMatch teamMatchA() {
        return TeamMatch.builder()
                .team(team2())
                .score(2)
                .scorers(List.of(PlayerScorer.builder()
                                .player(player3())
                                .scoreTime(70)
                                .isOG(false)
                                .build(),
                        PlayerScorer.builder()
                                .player(player6())
                                .scoreTime(80)
                                .isOG(true)
                                .build()))
                .build();
    }

    public static app.foot.controller.rest.Team team3() {
        return app.foot.controller.rest.Team.builder()
                .id(4)
                .name("E4")
                .build();
    }

    public static Player player6() {
        return Player.builder()
                .id(6)
                .name("J6")
                .teamName(team3().getName())
                .isGuardian(false)
                .build();
    }

    public static Player player3() {
        return Player.builder()
                .id(3)
                .name("J3")
                .teamName(team2().getName())
                .isGuardian(false)
                .build();
    }

    public static app.foot.controller.rest.Team team2() {
        return app.foot.controller.rest.Team.builder()
                .id(2)
                .name("E2")
                .build();
    }
    public static String formatThrowMessage(String message, HttpStatus status) {
        if (status != null) {
            return status.toString() + " : " + message;
        } return message;
    }

}


