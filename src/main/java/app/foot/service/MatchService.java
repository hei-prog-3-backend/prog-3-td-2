package app.foot.service;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.repository.MatchRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.mapper.MatchMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchService {
    private final MatchRepository repository;
    private final MatchMapper mapper;

    public List<Match> getMatches() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    public List<MatchEntity> postGoalsToMatch(List<PlayerScoreEntity> scorers, Integer idMatch) {
        Optional<MatchEntity> match = repository.findById(idMatch);
        if (match.isPresent()){
            List<PlayerScoreEntity> scorersTeam = new ArrayList<>();
            List<MatchEntity> matches = new ArrayList<>();

            for (PlayerScoreEntity scorer:scorers){
                if(!scorer.getPlayer().isGuardian() && scorer.getMinute() > 0 && scorer.getMinute() <= 90){
                    scorersTeam.add(scorer);
                }
            }
            match.get().setScorers(scorersTeam);
            matches.add(match.get());

            return repository.saveAll(matches);
        }
        else {
            return null;
        }
    }
}
