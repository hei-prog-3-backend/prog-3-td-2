package app.foot.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Player {
  private Integer id;
  private String name;
  private Boolean isGuardian;
}
