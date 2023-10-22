package app.web.pavelk.read2.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j(topic = "static-controller")
@Controller
@RequiredArgsConstructor
public class StaticController {

    @Value("${app.host:}")
    private String host;

    @PostConstruct
    public void pathsLog() {
        log.info("{}/api/read2/", host);
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/main/index.html";
    }

    @GetMapping("/main/")
    public String homePage() {
        return "redirect:/main/index.html";
    }

}

