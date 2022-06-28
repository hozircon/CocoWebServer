package com.example.webserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlller {
    @GetMapping("/index")
    public String indexStart(){
        return "index";
    }
}
