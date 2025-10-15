package com.codewithmosh.store.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Tag(name = "Home")
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Federico");

        return "index";
    }
}
