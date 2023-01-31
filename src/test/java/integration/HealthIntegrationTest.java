package integration;

import app.foot.FootApi;
import app.foot.controller.rest.Match;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
public class HealthIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void get_health_ok() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/ping")).andReturn().getResponse();


        Assertions.assertEquals(response.getContentAsString(),"pong");
    }
}
