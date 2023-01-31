package integration;

import app.foot.FootApi;
import app.foot.controller.rest.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.List;

import static integration.MatchIntegrationTest.teamMatchB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;




@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
public class PlayerScorerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    void add_goals_ok() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/matches/3/goals")
                        .content(objectMapper.writeValueAsString(List.of(scorer1())))
                        .contentType("application/json")
                        .accept("application/json")
                )
                .andReturn()
                .getResponse();

        Match actual = objectMapper.readValue(
                response.getContentAsString(), Match.class);
        assertEquals(match3(), actual);
    }

    void add_goals_ko() throws Exception{

    }

    public static Match match3(){
        return Match.builder()
                .id(3)
                .datetime(Instant.parse("2023-01-01T18:00:00Z"))
                .stadium("S3")
                .teamA(teamMatchA())
                .teamB(teamMatchB())
                .build();
    }

    public static TeamMatch teamMatchA() {
        return TeamMatch.builder()
                .team(team1())
                .score(1)
                .scorers(List.of(scorer1()))
                .build();
    }

    public static Team team1() {
        return Team.builder()
                .id(1)
                .name("E1")
                .build();
    }

    private static Player player1(){
        return Player.builder()
                .id(1)
                .teamName("E1")
                .isGuardian(false)
                .name("J1")
                .build();
    }
    private static PlayerScorer scorer1() {
        return PlayerScorer.builder()
                .scoreTime(20)
                .player(player1())
                .isOG(false)
                .build();
    }



    private List<PlayerScorer> convertFromHttpResponse(MockHttpServletResponse response)
            throws JsonProcessingException, UnsupportedEncodingException {
        CollectionType playerListType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, PlayerScorer.class);
        return objectMapper.readValue(
                response.getContentAsString(),
                playerListType);
    }

}
