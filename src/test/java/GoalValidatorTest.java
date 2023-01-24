import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import app.foot.controller.rest.Player;
import app.foot.controller.rest.PlayerScorer;
import app.foot.controller.validator.GoalValidator;
import org.junit.jupiter.api.Test;

public class GoalValidatorTest {
    GoalValidator subject = new GoalValidator();

    Player guardianPlayer() {
        return Player.builder()
                .id(0)
                .isGuardian(true)
                .build();
    }

    Player initPlayer() {
        return Player.builder()
                .id(1)
                .isGuardian(false)
                .build();
    }

    PlayerScorer validPlayerScorer() {
        return PlayerScorer.builder()
                .player(initPlayer())
                .scoreTime(1)
                .isOG(true)
                .build();
    }

    @Test
    void accept_ok() {
        assertDoesNotThrow(() -> subject.accept(validPlayerScorer()));
    }

    //Mandatory attributes not provided : scoreTime
    @Test
    void accept_ko() {
        PlayerScorer invalidPlayer = validPlayerScorer().builder()
                .scoreTime(null)
                .build();
        assertThrows(RuntimeException.class, () -> subject.accept(invalidPlayer),
                "Score minute is mandatory.");
    }

    @Test
    void when_guardian_throws_exception() {
        PlayerScorer invalidPlayer = validPlayerScorer().builder()
                .player(guardianPlayer())
                .build();
        assertThrows(RuntimeException.class, () -> subject.accept(invalidPlayer),
                "Player#" + invalidPlayer.getPlayer().getId()+" is a guardian so they cannot score.");
    }

    @Test
    void when_score_time_greater_than_90_throws_exception() {
        PlayerScorer invalidPlayer = validPlayerScorer().builder()
                .player(Player.builder()
                        .id(1)
                        .build())
                .scoreTime(91)
                .build();
        assertThrows(RuntimeException.class, () -> subject.accept(invalidPlayer),
                "Player#"+invalidPlayer.getPlayer().getId()+" cannot score before minute 0.");
    }

    @Test
    void when_score_time_less_than_0_throws_exception() {
        PlayerScorer invalidPlayer = validPlayerScorer().builder()
                .player(Player.builder()
                        .id(1)
                        .build())
                .scoreTime(-1)
                .build();
        assertThrows(RuntimeException.class, () -> subject.accept(invalidPlayer),
                "Player#"+invalidPlayer.getPlayer().getId()+" cannot score after minute 90.");
    }
}