package com.app.prosport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("prosport/")
public class UIController {

    @GetMapping(value = "")
    public String startPageHTML() {
        return "testModel";
    }
}
