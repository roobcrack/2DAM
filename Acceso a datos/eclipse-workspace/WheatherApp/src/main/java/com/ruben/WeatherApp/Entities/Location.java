package com.ruben.WeatherApp.Entities;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Location {

	@SerializedName("name")
	private String city;
    private Main main;
    private Sys sys;
    private List<Weather> weather;
    
    public static class Sys{
    	private String country;
    }
    
    public static class Main{
    	@SerializedName("temp")
        private double temp; 
    	private String humidity;
    	
    	public double getTempKelvin() {
    		return temp + 273.15;
    	}
    }
    
    @Data
    public static class Weather {
        private String main;
        private String description;
    }
    
    
	@Override
	public String toString() {
		return "City: " + city + "(" + sys.country + ")" +
				"\nTemperature: " +  main.temp + "º (" + String.format("%.2f", main.getTempKelvin()) + "K)" +
				"\nHumidity= " + main.humidity + "%" +
				"\nWeather: " + weather.get(0).main + "(" +  weather.get(0).description + ")";
	}
}
