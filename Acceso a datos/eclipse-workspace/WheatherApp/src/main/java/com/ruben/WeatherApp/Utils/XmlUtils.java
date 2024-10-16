package com.ruben.WeatherApp.Utils;


import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ruben.WeatherApp.Entities.Location;

public class XmlUtils {
	
	public static Document readXml(String url) {
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			return dBuilder.parse(url);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void readWeatherXml(String url) {
			Document doc = readXml(url);
			doc.getDocumentElement().normalize();
			NodeList nList;

			
			nList = doc.getElementsByTagName("city");
			Element eElement = (Element) nList.item(0);
			System.out.println("Country: " + eElement.getAttribute("name") + 
					"(" + eElement.getElementsByTagName("country").item(0).getTextContent()+")");

			nList = doc.getElementsByTagName("temperature");
            eElement = (Element) nList.item(0);
            System.out.println("Temperature: " + eElement.getAttribute("value") + "º (" + 
            		((String.format("%.2f",Double.parseDouble(eElement.getAttribute("value")) + 273.15)) + "K)"));
            
			nList = doc.getElementsByTagName("humidity");
            eElement = (Element) nList.item(0);
            System.out.println("Humidity: " + eElement.getAttribute("value") + eElement.getAttribute("unit"));
			
            nList = doc.getElementsByTagName("clouds");
            eElement = (Element) nList.item(0);
            System.out.print("Humidity: " + eElement.getAttribute("name"));

            nList = doc.getElementsByTagName("weather");
            eElement = (Element) nList.item(0);
            System.out.println(" (" + eElement.getAttribute("value") + ")");
			
	}
}
