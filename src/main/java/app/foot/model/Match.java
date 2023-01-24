package app.foot.model;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class Match {
    private Integer id;
    private TeamMatch teamA;
    private TeamMatch teamB;
    private String stadium;
    private Instant datetime;
}
