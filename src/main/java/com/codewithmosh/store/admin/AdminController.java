package com.codewithmosh.store.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
@Tag(name = "Admin")
public class AdminController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
}
