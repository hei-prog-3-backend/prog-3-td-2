package app.foot.controller.response;

import app.foot.model.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateScorersResponse {
        private Player player;
        private Integer minute;
        private Boolean isOwnGoal;
}
