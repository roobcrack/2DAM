package com.ruben.WebtherChecker.Utils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:4200")  // Permite llamadas desde Angular
public class WeatherController {

    @GetMapping("/current")
    public String getCurrentWeather() {
        String lat = "38.3451";
        String lon = "-0.4814";
        String apiKey = "30db9cad9c2ad82cbdf3d193bcd6c197";
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
                + "&lang=es&units=metric&appid=" + apiKey;

        // Consume la API y retorna los datos de la ubicación
        //return JsonUtils.readGeneric(url, Location.class);
        return "Connected";
    }
}
