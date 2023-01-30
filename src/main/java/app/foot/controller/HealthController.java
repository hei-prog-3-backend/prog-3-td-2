package app.foot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    //TODO: add integration test ok for this
    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }
}
