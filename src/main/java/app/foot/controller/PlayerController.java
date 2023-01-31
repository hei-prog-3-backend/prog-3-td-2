package app.foot.controller;

import app.foot.controller.exception.NotAllowedException;
import app.foot.controller.rest.Player;
import app.foot.controller.rest.mapper.PlayerRestMapper;
import app.foot.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PlayerController {
    private final PlayerRestMapper mapper;
    private final PlayerService service;

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return service.getPlayers().stream()
                .map(mapper::toRest)
                .collect(Collectors.toUnmodifiableList());
    }

    @PostMapping("/players")
    public List<Player> addPlayers(@RequestBody List<Player> toCreate) {
        List<app.foot.model.Player> domain = toCreate.stream()
                .map(mapper::toDomain)
                .toList();
        return service.createPlayers(domain).stream()
                .map(mapper::toRest)
                .toList();
    }

    //TODO: add PUT /players where you can modify the name and the guardian status of a player
    // Don't forget to add integration tests for this

    @PutMapping("/players")
    public List<Player> updatePlayers(@RequestBody List<Player> toUpdate){
        for ( Player playerToCheck : toUpdate ) {
            if (!Objects.equals(playerToCheck.getTeamName(), service.getPlayerById(playerToCheck.getId()).getTeamName())) {
                throw new NotAllowedException("Team's name can't be changed");
            }
        }
        List<app.foot.model.Player> domain = toUpdate.stream()
                .map(mapper::toDomain)
                .toList();
        return service.updatePlayers(domain).stream()
                .map(mapper::toRest)
                .toList();
    }
}
