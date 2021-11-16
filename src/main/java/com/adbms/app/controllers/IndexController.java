package com.adbms.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping()
public class IndexController {

    @GetMapping("/index")
    public String main(Model model) {
        return "index";
    }
}