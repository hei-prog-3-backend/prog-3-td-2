package integration;

import app.foot.FootApi;
import app.foot.controller.rest.*;
import app.foot.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.List;

import static integration.MatchIntegrationTest.teamMatchB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
@Transactional
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
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void add_goals_ko_guardian_scores() throws Exception{
        PlayerScorer badScorer1 = scorer1()
                .toBuilder()
                .player(player1().toBuilder().isGuardian(true).build()).build();

        MockHttpServletResponse response = mockMvc.perform(post("/matches/3/goals")
                        .content(objectMapper.writeValueAsString(List.of(badScorer1)))
                        .contentType("application/json")
                        .accept("application/json")
                )
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
    }

    @Test
    void score_time_greater_than_90() throws Exception{
        PlayerScorer badScorer1 = scorer1()
                .toBuilder()
                .scoreTime(100)
                .build();

        MockHttpServletResponse response = mockMvc.perform(post("/matches/3/goals")
                        .content(objectMapper.writeValueAsString(List.of(badScorer1)))
                        .contentType("application/json")
                        .accept("application/json")
                )
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();

    }

    @Test
    void score_time_less_than_1() throws Exception{
        PlayerScorer badScorer1 = scorer1()
                .toBuilder()
                .scoreTime(0)
                .build();

        MockHttpServletResponse response = mockMvc.perform(post("/matches/3/goals")
                        .content(objectMapper.writeValueAsString(List.of(badScorer1)))
                        .contentType("application/json")
                        .accept("application/json")
                )
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
    }

    @Test
    void score_time_null() throws Exception{
        PlayerScorer badScorer1 = scorer1()
                .toBuilder()
                .scoreTime(null)
                .build();

        MockHttpServletResponse response = mockMvc.perform(post("/matches/3/goals")
                        .content(objectMapper.writeValueAsString(List.of(badScorer1)))
                        .contentType("application/json")
                        .accept("application/json")
                )
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
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
