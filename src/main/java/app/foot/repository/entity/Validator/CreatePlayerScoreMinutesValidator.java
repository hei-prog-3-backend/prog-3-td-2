package app.foot.repository.entity.Validator;

import app.foot.exception.NotFoundException;
import app.foot.model.PlayerScorer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@AllArgsConstructor
@Component
public class CreatePlayerScoreMinutesValidator implements Consumer<PlayerScorer>{

        @Override public void accept(PlayerScorer playerScorer){
            if(!(playerScorer.getMinute() >= 0 && playerScorer.getMinute() <= 90)){
               throw new NotFoundException("A goal is not in time");
            }
        }
    }

