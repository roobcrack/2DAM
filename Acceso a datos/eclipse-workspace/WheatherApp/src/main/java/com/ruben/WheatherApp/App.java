package com.ruben.WheatherApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.ruben.WeatherApp.Entities.Location;
import com.ruben.WeatherApp.Utils.FilesUtils;
import com.ruben.WeatherApp.Utils.JsonUtils;
import com.ruben.WeatherApp.Utils.XmlUtils;

/**
 * author: Ruben Martinez Martinez
 */
public class App {
	public static void main(String[] args) {
		// showWheatherJson();
		// System.out.println("--------------------");
		// showWheatherXml();
		readTemperaturesFile();
	}

	public static void showWheatherJson() {
		String lat = "38.3451";
		String lon = "-0.4814";
		String apiKey = "30db9cad9c2ad82cbdf3d193bcd6c197";
		String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&lang=es"
				+ "&units=metric" + "&appid=" + apiKey;

		System.out.println(JsonUtils.readGeneric(url, Location.class).toString());
	}

	public static void showWheatherXml() {
		String city = "Alicante";
		String apiKey = "30db9cad9c2ad82cbdf3d193bcd6c197";
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city
				+ "&lang=es&units=metric&mode=xml&appid=" + apiKey;

		XmlUtils.readWeatherXml(url);
	}

	public static void readTemperaturesFile() {
		String dateFrom = "2019-01-01";
        String dateTo = "2019-03-30";

        // Suponiendo que las fechas están en el índice 5
        Map<String, String> temperaturesMap = FilesUtils.returnFileListed("C://ficheros/datos.csv").stream()
            .filter(e -> {
                String date = e.split(",")[5];
                return date.compareTo(dateFrom) >= 0 && date.compareTo(dateTo) <= 0;  // Filtra por rango de fechas
            })
            .collect(Collectors.toMap(
                e -> e.substring(81, 86).trim(),   // Extrae la hora
                e -> e.split(",")[6],              // Extrae la temperatura
                (oldValue, newValue) -> oldValue,  // Si hay duplicados, mantiene el primero
                TreeMap::new))                     // Ordenar por clave (horas)
            .entrySet().stream()                   // Para la impresión
            .peek(entry -> System.out.print(entry.getKey() + "->" + entry.getValue() + "º "))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));  // Recolectar el Map
	}
}
