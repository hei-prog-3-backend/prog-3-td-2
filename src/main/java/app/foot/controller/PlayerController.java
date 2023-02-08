package app.foot.controller;

import app.foot.controller.rest.Player;
import app.foot.controller.rest.mapper.PlayerRestMapper;
import app.foot.repository.PlayerRepository;
import app.foot.repository.entity.PlayerEntity;
import app.foot.service.PlayerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PlayerController {
    private final PlayerRestMapper mapper;
    private final PlayerService service;

    private final PlayerRepository repository;

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
                .collect(Collectors.toUnmodifiableList());
        return service.createPlayers(domain).stream()
                .map(mapper::toRest)
                .collect(Collectors.toUnmodifiableList());
    }

    //TODO: add PUT /players where you can modify the name and the guardian status of a player

    @PutMapping("/players/{id}")
    public PlayerEntity updatePlayers(@PathVariable Integer id,
                                      @RequestParam String playerName,
                                      @RequestParam Boolean isGuardian) {
        service.updateNamePlayer(id, playerName);
        service.updateGuardianPlayer(id, isGuardian);
        return service.getById(id);
    }



    // Don't forget to add integration tests for this
}
