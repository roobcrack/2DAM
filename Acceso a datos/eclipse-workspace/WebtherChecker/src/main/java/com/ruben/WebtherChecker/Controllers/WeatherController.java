package com.ruben.WebtherChecker.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private String chain = "testtt";
    
    @GetMapping("/current")
    public String getCurrentWeather() {
        return chain;
    }
}
