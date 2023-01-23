package app.foot.repository;

import app.foot.repository.entity.PlayerScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerScoreRepository extends JpaRepository<PlayerScoreEntity, Integer> {
}
