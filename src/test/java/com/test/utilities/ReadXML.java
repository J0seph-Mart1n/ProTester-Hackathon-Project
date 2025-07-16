package com.test.utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class ReadXML  {
	static String filePath = System.getProperty("user.dir") + "//src//test//resources//";
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private static Document doc;
	
	public ReadXML() throws ParserConfigurationException {
		this.factory = DocumentBuilderFactory.newInstance();
		this.builder = factory.newDocumentBuilder();
		doc = builder.newDocument();
	}
	
	public static Document initializeXML(String fileName) {
		try {
			File file = new File(filePath+fileName);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(file);
			doc.getDocumentElement().normalize();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Data nreading from json is unsuccessful");
		}
		
		return doc;
	}
	
	public String[] getContactValidData(){
		Document doc = initializeXML("ContactValidForm.xml");
		String[] carData = new String[5];
		try {
			carData[0] = doc.getElementsByTagName("name").item(0).getTextContent();
			carData[1] = doc.getElementsByTagName("email").item(0).getTextContent();
			carData[2] = doc.getElementsByTagName("number").item(0).getTextContent();
			carData[3] = doc.getElementsByTagName("message").item(0).getTextContent();
			carData[4] = doc.getElementsByTagName("result").item(0).getTextContent();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Data nreading from json is unsuccessful");
		}
		return carData;
	}
	
	public String[] getContactInvalidData(){
		Document doc = initializeXML("ContactInvalidForm.xml");
		String[] carData = new String[5];
		try {
			carData[0] = doc.getElementsByTagName("name").item(0).getTextContent();
			carData[1] = doc.getElementsByTagName("email").item(0).getTextContent();
			carData[2] = doc.getElementsByTagName("number").item(0).getTextContent();
			carData[3] = doc.getElementsByTagName("message").item(0).getTextContent();
			carData[4] = doc.getElementsByTagName("result").item(0).getTextContent();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Data nreading from json is unsuccessful");
		}
		return carData;
	}
	
	
		

}
