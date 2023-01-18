package app.foot.controller;

import app.foot.model.Goal;
import app.foot.service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@RestController
@AllArgsConstructor
public class GoalController {

    private final GoalService goalService;
    @GetMapping("/goals")

    @PostMapping("/matches/{matchId}/goals")
    public List<Goal> createGoals(
            @PathVariable String matchId, @RequestBody List<Goal> toCreate) {
        return goalService.addGoal();
    }
}
