package com.ruben.WebtherChecker.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruben.WebtherChecker.Entities.Location;
import com.ruben.WebtherChecker.Utils.JsonUtils;

@RestController
@RequestMapping("/api")

public class MyController {

    private int counter = 0;

    @GetMapping("/data")
    public String getData() {
        counter++;
        return String.valueOf(counter);
    }
    
    @GetMapping("/testing")
    public String getLocation() {
		String lat = "38.3451";
		String lon = "-0.4814";
		String apiKey = "30db9cad9c2ad82cbdf3d193bcd6c197";
		String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&lang=es"
				+ "&units=metric" + "&appid=" + apiKey;

        return JsonUtils.toJson(JsonUtils.readGeneric(url, Location.class));
    }
    
    @PostMapping("/submit")
    public ResponseEntity<String> submitData(@RequestBody Coordinates coordinates) {
        // Process the received coordinates
        System.out.println("Received coordinates: " + coordinates);
        return ResponseEntity.ok("Data received successfully!");
    }
    
    class Coordinates {
        private double altitude;
        private double latitude;

        // Getters and setters
        public double getAltitude() {
            return altitude;
        }

        public void setAltitude(double altitude) {
            this.altitude = altitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }
}