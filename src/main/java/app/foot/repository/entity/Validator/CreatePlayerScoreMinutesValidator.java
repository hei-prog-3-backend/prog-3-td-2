package app.foot.repository.entity.Validator;

import app.foot.exception.NotFoundException;
import app.foot.model.PlayerScorer;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

public class CreatePlayerScoreMinutesValidator {

    @Component
    public class CreateScoreValidatorMinutes implements Consumer<PlayerScorer>{
        @Override public void accept(PlayerScorer playerScorer){
            if(!(playerScorer.getMinute() == 0 && playerScorer.getMinute() <= 90)){
               throw new NotFoundException("Le but n'est pas entre temps");
            }
        }
    }
}
