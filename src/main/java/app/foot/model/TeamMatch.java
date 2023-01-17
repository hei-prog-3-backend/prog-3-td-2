package app.foot.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class TeamMatch {
    private Team team;
    private Integer score;
    private List<PlayerScorer> scorers;
}
