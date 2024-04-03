package com.artoo.artooting.controller;

import com.artoo.artooting.service.ArtooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ArtooController {
    @Autowired
    private ArtooService artooService;

    @GetMapping()
    public String home(){
        return "index";
    }

    @GetMapping("/generic")
    public String generic(){
        return "generic";
    }

    @GetMapping("/comments")
    public String comments(){
        return "elements";
    }
}
