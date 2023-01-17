package app.foot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@RestController
@AllArgsConstructor
public class GoalController {
    @GetMapping("/goals")
    @PostMapping("/matches/{matchId}/goals")
    public List<Goals> createGoals(
            @PathVariable String matchId, @RequestBody List<CreateGoal> toCreate) {
        return goalService.saveAll(
                        feeMapper.toDomainFee(studentId, toCreate)).stream()
                .map(feeMapper::toRestFee)
                .collect(toUnmodifiableList());
    }
}
