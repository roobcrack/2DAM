package com.ruben.WebtherChecker.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private int counter = 0;

    @GetMapping("/data")
    public String getData() {
        counter++;
        return String.valueOf(counter);  // Incrementa y devuelve como cadena
    }
}