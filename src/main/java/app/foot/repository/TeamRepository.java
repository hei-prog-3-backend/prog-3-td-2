package app.foot.repository;

import app.foot.repository.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    TeamEntity findByName(String name);
}
