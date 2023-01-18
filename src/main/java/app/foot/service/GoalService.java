package app.foot.service;

import app.foot.model.Goal;
import app.foot.model.Match;
import app.foot.repository.GoalRepository;
import app.foot.repository.mapper.GoalMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;

    private final GoalMapper goalMapper;

    public List<Goal> getGoals() {
        return goalRepository.findAll().stream()
                .map(goalMapper::toDomain)
                .toList();
    }

    public List<Goal> addGoal() {
        return goalRepository.save();
    }
}
