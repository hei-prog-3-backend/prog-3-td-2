import app.foot.controller.rest.Player;
import app.foot.controller.rest.PlayerScorer;
import app.foot.controller.validator.GoalValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GoalValidatorTest {
    GoalValidator subject = new GoalValidator();

    private static PlayerScorer scorer1() {
        return PlayerScorer.builder()
                .player(player1())
                .isOG(false)
                .scoreTime(10)
                .build();
    }

    private static PlayerScorer nullScoreTimeScorer() {
        return scorer1().toBuilder()
                .scoreTime(null)
                .build();
    }

    private static Player player1() {
        return Player.builder()
                .id(1)
                .name("Rakoto")
                .isGuardian(false)
                .build();
    }

    @Test
    void accept_ok() {
        assertDoesNotThrow(() -> subject.accept(scorer1()));
    }

    //Mandatory attributes not provided : scoreTime
    @Test
    void accept_ko() {
        assertThrows(RuntimeException.class, () -> subject.accept(nullScoreTimeScorer()));
    }

    @Test
    void when_guardian_throws_exception() {
        assertThrows(RuntimeException.class, () -> subject.accept(
                scorer1().toBuilder()
                        .player(player1().toBuilder()
                                .isGuardian(true)
                                .build())
                        .build()));
    }

    @Test
    void when_score_time_greater_than_90_throws_exception() {
        assertThrows(RuntimeException.class, () -> subject.accept(
                scorer1().toBuilder()
                        .scoreTime(91)
                        .build()));
    }

    @Test
    void when_score_time_less_than_0_throws_exception() {
        assertThrows(RuntimeException.class, () -> subject.accept(
                scorer1().toBuilder()
                        .scoreTime(-1)
                        .build()));
    }
}
