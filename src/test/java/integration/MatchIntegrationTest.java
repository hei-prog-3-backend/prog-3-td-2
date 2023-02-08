package integration;

import app.foot.FootApi;
import app.foot.controller.rest.*;
import app.foot.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static utils.TestUtils.*;

@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
class MatchIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules();  //Allow 'java.time.Instant' mapping

    @Test
    void read_match_by_id_ok() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/matches/2"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        Match actual = objectMapper.readValue(
                response.getContentAsString(), Match.class);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedMatch2(), actual);
    }



    @Test
    void read_match_ok() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/matches"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        List<Match> actual = convertFromHttpResponse(response);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(actual.contains(expectedMatch3()));
        assertTrue(actual.contains(expectedMatch2()));
        assertTrue(actual.contains(expectedMatch1()));

    }

    void read_match_ko (){
        // GET /matches
    }
@Test
    void create_goals_ok() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(post("/matches/3/goals")
                        .content(objectMapper.writeValueAsString(List.of(playerScorer())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        Match actual = objectMapper.readValue(response.getContentAsString(),Match.class);
        assertTrue(actual.getTeamA().getScorers().contains(playerScorer()));

}




    @Test
    void add_goals_to_match_ko() throws Exception {
        PlayerScorer nullTimeScorer = scorer1().toBuilder()
                .scoreTime(null)
                .build();

        RequestBuilder request = post("/matches/3/goals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(List.of(nullTimeScorer)));

        ServletException exception = assertThrows(ServletException.class, () -> mockMvc.perform(request));

        String exceptionMessage = formatThrowMessage(
                "Score minute is mandatory.",
                HttpStatus.BAD_REQUEST
        );

        assertThrowsExceptionMessage(exceptionMessage, BadRequestException.class, () -> {
            throw exception.getRootCause();
        });
    }





    public static Match expectedMatch1() {
        return Match.builder()
                .id(1)
                .teamA(teamMatchA1())
                .teamB(teamMatchB1())
                .stadium("S1")
                .datetime(Instant.parse("2023-01-01T10:00:00Z"))
                .build();
    }
    public static TeamMatch teamMatchA1() {
        return TeamMatch.builder()
                .team(team1())
                .score(4)
                .scorers(List.of(PlayerScorer.builder()
                                  .player(player1())
                                .scoreTime(30)
                                .isOG(false)
                                .build(),
                        PlayerScorer.builder()
                                .player(player1())
                                .scoreTime(20)
                                .isOG(false)
                                .build(),
                                PlayerScorer.builder()
                                        .player(player1())
                                        .scoreTime(10)
                                        .isOG(false)
                                        .build(),
                                PlayerScorer.builder()
                                        .player(player4())
                                        .scoreTime(60)
                                        .isOG(true)
                                        .build()
                        )
                        )
                .build();
    }

    public static TeamMatch teamMatchB1() {
        return TeamMatch.builder()
                .team(team2())
                .score(2)
                .scorers(List.of(PlayerScorer.builder()
                                        .player(player2())
                                        .scoreTime(40)
                                        .isOG(true)
                                        .build(),
                                PlayerScorer.builder()
                                        .player(player3())
                                        .scoreTime(50)
                                        .isOG(false)
                                        .build()
                        )
                )
                .build();
    }

    private static Team team1() {
        return Team.builder()
                .id(1)
                .name("E1")
                .build();
    }

    private static Team team2() {
        return Team.builder()
                .id(2)
                .name("E2")
                .build();
    }

    public static Player player1() {
        return Player.builder()
                .id(1)
                .name("J1")
                .teamName(team1().getName())
                .isGuardian(false)
                .build();
    }
    public static Player player2() {
        return Player.builder()
                .id(2)
                .name("J2")
                .teamName(team1().getName())
                .isGuardian(false)
                .build();
    }
    public static Player player3() {
        return Player.builder()
                .id(3)
                .name("J3")
                .teamName(team2().getName())
                .isGuardian(false)
                .build();
    }
    public static Player player4() {
        return Player.builder()
                .id(4)
                .name("J4")
                .teamName("E2")
                .isGuardian(false)
                .build();
    }






    private static Match expectedMatch2() {
        return Match.builder()
                .id(2)
                .teamA(teamMatchA())
                .teamB(teamMatchB())
                .stadium("S2")
                .datetime(Instant.parse("2023-01-01T14:00:00Z"))
                .build();
    }

    private static TeamMatch teamMatchB() {
        return TeamMatch.builder()
                .team(team3())
                .score(0)
                .scorers(List.of())
                .build();
    }

    private static TeamMatch teamMatchA() {
        return TeamMatch.builder()
                .team(team2())
                .score(2)
                .scorers(List.of(PlayerScorer.builder()
                                .player(player3())
                                .scoreTime(70)
                                .isOG(false)
                                .build(),
                        PlayerScorer.builder()
                                .player(player6())
                                .scoreTime(80)
                                .isOG(true)
                                .build()))
                .build();
    }

    private static Team team3() {
        return Team.builder()
                .id(3)
                .name("E3")
                .build();
    }

    private static Player player6() {
        return Player.builder()
                .id(6)
                .name("J6")
                .teamName(team3().getName())
                .isGuardian(false)
                .build();
    }

    public static Match expectedMatch3() {
        return Match.builder()
                .id(3)
                .teamA(teamMatchA3())
                .teamB(teamMatchB3())
                .stadium("S3")
                .datetime(Instant.parse( "2023-01-01T18:00:00Z"))
                .build();
    }

    private static TeamMatch teamMatchB3() {
        return TeamMatch.builder()
                .team(team3())
                .score(0)
                .scorers(List.of())
                .build();
    }

    private static TeamMatch teamMatchA3() {
        return TeamMatch.builder()
                .team(team1())
                .score(9)
                .scorers(List.of(
                        playerScorer(),
                        playerScorer(),
                        playerScorer(),
                        playerScorer(),
                        playerScorer(),
                        playerScorer(),
                        playerScorer(),
                        playerScorer(),
                        playerScorer()))
                .build();
    }
    private List<Match> convertFromHttpResponse(MockHttpServletResponse response)
            throws JsonProcessingException, UnsupportedEncodingException {
        CollectionType matchListType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, Match.class);
        return objectMapper.readValue(
                response.getContentAsString(),
                matchListType);
    }

}
