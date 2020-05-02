package com.springtraining.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/leader")
    public String leaders(){
        return "leader";
    }


    @GetMapping("/systems")
    public String systems(){
        return "systems";
    }

    @GetMapping("/accessDen")
    public String accessDen(){
        return "accessDen";
    }
}
