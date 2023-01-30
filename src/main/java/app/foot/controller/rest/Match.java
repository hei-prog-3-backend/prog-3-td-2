package app.foot.controller.rest;

import java.time.Instant;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Match {
  private Integer id;
  private TeamMatch teamA;
  private TeamMatch teamB;
  private String stadium;
  private Instant datetime;
}
