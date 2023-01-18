package app.foot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import app.foot.repository.PlayerRepository;
import app.foot.repository.entity.PlayerEntity;


@AllArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerEntity getPlayerById(int id){
        return repository.getReferenceById(id);
    }
}
