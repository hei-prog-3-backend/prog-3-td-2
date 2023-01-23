package app.foot.repository.entity;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.model.Team;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Entity(name = "goal")
@Data
@EqualsAndHashCode
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "matchId", nullable = false)
    private List<Match> match;

    @JoinColumn(name = "playerType", nullable = false)
    private List<PlayerEntity> playerType;
}
