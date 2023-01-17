package app.foot.controller.rest.mapper;

import app.foot.controller.rest.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamRestMapper {
  public Team toRest(app.foot.model.Team domain) {
    return Team.builder()
        .id(domain.getId())
        .name(domain.getName())
        .build();
  }
}
