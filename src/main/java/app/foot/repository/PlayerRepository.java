package app.foot.repository;

import app.foot.repository.entity.PlayerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
  Optional<PlayerEntity> findByIdAndNameAndGuardian(int id, String name, boolean isGuardian);
}
