package app.foot.repository.entity.Validator;

import java.util.function.Consumer;

import app.foot.model.Player;
import app.foot.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CreatePlayerScoreValidator implements Consumer<Player> {

        @Override public void accept(Player player) {
            if (player.getIsGuardian() == true) {
                throw new NotFoundException("Guardian cannot goal");
            }
        }
    }

