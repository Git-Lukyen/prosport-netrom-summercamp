package com.app.prosport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("prosport/")
public class UIController {

    @GetMapping(value = "")
    public String welcomeAsHTML() {
        return "testModel";
    }
}
