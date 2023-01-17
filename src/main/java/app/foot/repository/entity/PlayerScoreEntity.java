package app.foot.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "player_score")
@Data
public class PlayerScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_match")
    private MatchEntity match;
    @ManyToOne
    @JoinColumn(name = "id_player")
    private PlayerEntity player;
    private Integer minute;
    private boolean ownGoal;
}
