package app.foot.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity(name = "match")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "match")
    private List<PlayerScoreEntity> scorers;
    @ManyToOne
    @JoinColumn(name = "id_team_A")
    private TeamEntity teamA;
    @ManyToOne
    @JoinColumn(name = "id_team_B")
    private TeamEntity teamB;
    private String stadium;
    @JoinColumn(name = "\"datetime\"")
    private Instant datetime;
}
