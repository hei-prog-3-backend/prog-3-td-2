package app.foot.controller.rest;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode
@ToString
public class TeamMatch {
  private Team team;
  private Integer score;
  private List<PlayerScorer> scorers;
}
