package app.foot.repository;

import app.foot.model.Goal;
import app.foot.repository.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {
    List<Goal> save();
}