package com.test.utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ReadXML {
	public static String name;
	public static String organization;
	public static String contactNumber;
	public static String emailId;
	public static String organizationSize;
	public static String interestedIn;
 
    public static void readData() {
 
    	//locationData = getTestData("loc_value");
        //System.out.println("Received Location data is : " + locationData);
 
        //searchData = getTestData("search_value");
        //System.out.println("Received Search data is : " + searchData);
        name = getTestData("name_value");
        organization = getTestData("org_value");
        contactNumber = getTestData("number");
        emailId = getTestData("email_id");
        organizationSize = getTestData("sizeoforg");
        interestedIn = getTestData("interest");
        }
 
    private static String getTestData(String nodeName) {
        String finalValue = null;
        try {
            File xmlFile = new File("src\\test\\resources\\FormData.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
 
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = String.format("/FormData/form//%s", nodeName);
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
 
            if (nodeList.getLength() > 0) {
                finalValue = nodeList.item(0).getTextContent().trim();
            }
        } catch (Exception e) {
            System.err.println("Error reading XML data: " + e.getMessage());
        }
        return finalValue;
    }
}
