package app.foot.service;

import app.foot.model.Match;
import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerRepository;
import app.foot.repository.PlayerScoreRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.mapper.MatchMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerScoreService {

    private final PlayerScoreRepository repository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;


    public List<PlayerScoreEntity> createPlayerScorer(Integer matchId, List<PlayerScorer> playerScorers){

        if(matchId == null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"matchId should be an integer or not null");
        }

        List<PlayerScoreEntity> playerScoreEntities = new ArrayList<>();

        MatchEntity match = matchRepository.findById(matchId).get();
        if(match == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Match " + match + " not found");
        }

        for(int i = 0; i < playerScorers.size(); i++){
            PlayerEntity playerEntity = playerRepository.findById(playerScorers.get(i).getPlayer().getId()).get();
            if(playerEntity.isGuardian()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A guardian should not score");
            }
            else if (playerScorers.get(i).getMinute() < 0 || playerScorers.get(i).getMinute() > 90) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Scoring time should be between 0 and 90");
            }
            else {
            playerScoreEntities.add(
            PlayerScoreEntity.builder()
                            .match(match)
                    .player(playerEntity)
                    .minute(playerScorers.get(i).getMinute())
                    .ownGoal(playerScorers.get(i).getIsOwnGoal())
                    .build()
                );
            }
        }
        return repository.saveAll(playerScoreEntities);

    }
}
