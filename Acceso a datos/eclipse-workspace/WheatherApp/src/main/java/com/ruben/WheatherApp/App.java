package com.ruben.WheatherApp;

import com.ruben.WeatherApp.Entities.Location;
import com.ruben.WeatherApp.Utils.InternetUtils;
import com.ruben.WeatherApp.Utils.JsonUtils;

/**
 * author: Ruben Martinez Martinez
 */
public class App {
    public static void main(String[] args) {
    	String apiKey = "30db9cad9c2ad82cbdf3d193bcd6c197";
        String lat = "38.3452";
        String lon = "-0.4815";
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+apiKey;
        		
    	Location location = JsonUtils.readGeneric(url, Location.class);
    
    System.out.println(location.toString());
    }
}
