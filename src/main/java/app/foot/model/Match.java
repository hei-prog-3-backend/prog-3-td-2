package app.foot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Match {
    private Integer id;
    private TeamMatch teamA;
    private TeamMatch teamB;
    private String stadium;
    private Instant datetime;
}
