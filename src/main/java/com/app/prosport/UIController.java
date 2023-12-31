package com.app.prosport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("prosport/")
public class UIController {

    @GetMapping(value = "")
    public String homePageHTML() {
        return "testModel";
    }

    @GetMapping(value = "teams-section")
    public String teamsPageHTML() {
        return "teamsPageRedirect";
    }

    @GetMapping(value = "teams-section/id/{id}")
    public String indivTeamPageHTML(@PathVariable(value = "id") Integer ID) {
        return "individualTeamPage";
    }

    @GetMapping(value = "players-section")
    public String playersPageHTML() {
        return "playersPageRedirect";
    }

    @GetMapping(value = "players-section/id/{id}")
    public String indivPlayerPageHTML(@PathVariable(value = "id") Integer ID) {
        return "individualPlayerPage";
    }

    @GetMapping(value = "comp-section")
    public String compPageHTML() {
        return "compPageRedirect";
    }

    @GetMapping(value = "comp-section/id/{id}")
    public String indivCompPageHTML(@PathVariable(value = "id") Integer ID) {
        return "individualCompPage";
    }

    @GetMapping(value = "testing-page")
    public String testPageHTML() {
        return "testPage";
    }
}
