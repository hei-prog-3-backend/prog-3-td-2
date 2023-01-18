package app.foot.repository.entity.Validator;

import java.util.function.Consumer;

import app.foot.model.Player;
import app.foot.exception.NotFoundException;
import org.springframework.stereotype.Component;

public class CreatePlayerScoreValidator {

    @Component
    public class CreateScoreValidatorisGuardian implements Consumer<Player> {
        @Override public void accept(Player player) {
            if (player.getIsGuardian() == true) {
                throw new NotFoundException("Un gardien de but ne peut pas marquer un but");
            }
        }
    }
}
