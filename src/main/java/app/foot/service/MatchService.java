package app.foot.service;

import app.foot.model.Match;
import app.foot.repository.MatchRepository;
import app.foot.repository.mapper.MatchMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Match> getMatch(Integer matchId) {
        return repository.findById(matchId).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
