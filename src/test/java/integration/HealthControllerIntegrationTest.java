package integration;


import app.foot.FootApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
public class HealthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_health_ok () throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/ping"))
                .andReturn()
                .getResponse();
        String actual = response.getContentAsString();
        assertEquals("pong", actual);
    }

}
