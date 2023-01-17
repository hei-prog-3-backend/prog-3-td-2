package app.foot.repository.mapper;

import app.foot.model.Team;
import app.foot.repository.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public Team toDomain(TeamEntity entity) {
        return Team.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
