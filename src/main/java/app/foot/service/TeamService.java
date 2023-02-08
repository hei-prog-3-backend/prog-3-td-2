package app.foot.service;

import app.foot.controller.exception.NotFoundException;
import app.foot.repository.TeamRepository;
import app.foot.repository.entity.TeamEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamEntity getTeamByName(String name){
        TeamEntity team = teamRepository.findByName(name);
        if(team == null){
            throw new NotFoundException("Team not found");
        }
        return team;
    }

}
