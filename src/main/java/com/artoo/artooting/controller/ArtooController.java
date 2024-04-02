package com.artoo.artooting.controller;

import com.artoo.artooting.service.ArtooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArtooController {
    @Autowired
    private ArtooService artooService;
}
