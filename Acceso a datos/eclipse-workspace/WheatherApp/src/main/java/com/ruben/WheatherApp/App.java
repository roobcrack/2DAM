package com.ruben.WheatherApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ruben.WeatherApp.Entities.Location;
import com.ruben.WeatherApp.Utils.FilesUtils;
import com.ruben.WeatherApp.Utils.JsonUtils;
import com.ruben.WeatherApp.Utils.SerializeUtils;
import com.ruben.WeatherApp.Utils.XmlUtils;

/**
 * author: Ruben Martinez Martinez
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
	/*	showWheatherJson();
		System.out.println("--------------------");
		showWheatherXml();
		System.out.println("--------------------");
		readTemperaturesFile();
		System.out.println("--------------------");
		System.out.println(SerializeUtils.deserializeObject("C:/ficheros/" + LocalDate.now().toString() + ".txt").toString()); */
	}

	public static void showWheatherJson() {
		String lat = "38.3451";
		String lon = "-0.4814";
		String apiKey = "30db9cad9c2ad82cbdf3d193bcd6c197";
		String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&lang=es"
				+ "&units=metric" + "&appid=" + apiKey;

		Location location = JsonUtils.readGeneric(url, Location.class);
		System.out.println(location.toString());
		SerializeUtils.serializeObject("C:/ficheros/" + LocalDate.now().toString() + ".txt",
				JsonUtils.readGeneric(url, Location.class));
	}

	public static void showWheatherXml() {
		String city = "Alicante";
		String apiKey = "30db9cad9c2ad82cbdf3d193bcd6c197";
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city
				+ "&lang=es&units=metric&mode=xml&appid=" + apiKey;

		System.out.println(XmlUtils.readWeatherXml(url).toString());
	}

	public static void readTemperaturesFile() {
		String dateFrom = "2019-01-01";
        String dateTo = "2019-01-30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        
        List<LocalDate> dateList = startDate.datesUntil(endDate.plusDays(1))
                .collect(Collectors.toList());
        
        List<Map<String, String>> temperaturesList = dateList.stream()
            .map(date -> {
                String currentDate = date.format(formatter);
                System.out.print(currentDate + " ");
                
                Map<String, String> temperaturesMap = FilesUtils.returnFileListed("C://ficheros/datos.csv").stream()
                        .filter(e -> e.split(",")[5].contains(currentDate))
                        .collect(Collectors.toMap(
                                e -> e.substring(81, 86),
                                e -> e.split(",")[6],
                                (oldValue, newValue) -> oldValue,
                                TreeMap::new));
                
                temperaturesMap.forEach((hour, temperature) -> System.out.print(hour + "->" + temperature + " "));
                System.out.println();
                
                return temperaturesMap;
            })
            .collect(Collectors.toList());
    }
}
