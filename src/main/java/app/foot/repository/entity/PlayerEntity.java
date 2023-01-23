package app.foot.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "player")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "\"name\"")
    private String name;
    private boolean guardian;
    @ManyToOne
    @JoinColumn(name = "id_team")
    private TeamEntity team;
}
