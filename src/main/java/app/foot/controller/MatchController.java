package app.foot.controller;

import app.foot.model.Match;
import app.foot.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@RestController
@AllArgsConstructor
public class MatchController {
    private final MatchService service;

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return service.getMatches();
    }


}
