package com.ruben.xmljson.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlUtils extends DefaultHandler {
	public static void processXmlSax() {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			DefaultHandler eventsHandler = new DefaultHandler() {
				String currentTag = "";
				String content = "";

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					currentTag = qName;
					if (currentTag == "asignatura")
						System.out.println("Asignatura: " + attributes.getValue("id"));
				}

				public void characters(char ch[], int start, int length) throws SAXException {
					content = new String(ch, start, length);
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (currentTag != "") {
						System.out.println(" " + currentTag + ": " + content);
						currentTag = "";
					}
				}
			};
			saxParser.parse("C:/ficheros/asignatura.xml", eventsHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void processXmlDom() {

		try {
			File inputFile = new File("C:/ficheros/asignatura.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Elemento base : " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("asignatura");
			System.out.println();
			System.out.println("Recorriendo asignaturas...");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Codigo: " + eElement.getAttribute("id"));
					System.out.println("Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
					System.out.println(
							"Ciclo: " + eElement.getElementsByTagName("cicloFormativo").item(0).getTextContent());
					System.out.println("Curso: " + eElement.getElementsByTagName("curso").item(0).getTextContent());
					System.out
							.println("Profesor: " + eElement.getElementsByTagName("profesor").item(0).getTextContent());
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
