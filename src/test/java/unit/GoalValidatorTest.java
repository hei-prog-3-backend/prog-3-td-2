package unit;

import app.foot.controller.validator.GoalValidator;
import app.foot.controller.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static utils.TestUtils.*;

public class GoalValidatorTest {
    GoalValidator subject = new GoalValidator();

    @Test
    void accept_ok() {
        assertDoesNotThrow(() -> subject.accept(scorer1()));
    }

    //Mandatory attributes not provided : scoreTime
    @Test
    void accept_ko() {
        assertThrowsExceptionMessage("Score minute is mandatory.",
                BadRequestException.class, () -> subject.accept(nullScoreTimeScorer()));
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
